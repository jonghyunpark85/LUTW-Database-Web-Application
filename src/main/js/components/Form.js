import { html, useEffect, useRef, useState, useMemo} from 'htm/preact/standalone.mjs'
import { useQuestions } from '../utilities/reducer.js'
import { curry, lazyPost } from '../utilities/utilities.js'
import { up, down, trash } from '../utilities/svg.js'
import Question from './Question.js'
import Toggle from './Toggle.js'

const newQuestion = (type) => ({
    id : '',
    prompt : 'New Question',
    subtitle : '',
    type : type,
    aggregate : '',
});

function Form({ edit = false }) {
    const [template, getTemplate] = useQuestions([]);
    const [questions, setQuestions] = useState([]);
    const [active, setActive] = useState(0);
    const container = useRef(null);

    useEffect(() => {
        setQuestions(template.questions);
    }, [template]);
    
    useEffect(() => {
        const unSelect = (e) => {
            if (!container.current.contains(e.target)) {
                setActive(null);
            }
        };
        document.addEventListener('click', unSelect);
    }, []);

    const add = curry((i, type, e) => {
        e.stopPropagation();
        const question = newQuestion(type);
        setQuestions([...questions.slice(0, i + 1), question, ...questions.slice(i + 1, questions.length)]);
        setActive(active + 1);
    });

    const update = curry((i, update) => {
        setQuestions([...questions.slice(0, i), { ...questions[i], ...update }, ...questions.slice(i + 1, questions.length)]);
    });

    const moveDown = curry((i, e) => {
        if (i >= questions.length - 1) return;
        
        e.stopPropagation();
        setQuestions([...questions.slice(0, i), questions[i+1], questions[i], ...questions.slice(i + 2, questions.length)]);  
        setActive(active + 1);
    });

    const moveUp = curry((i, e) => {
        if (i === 0) return;

        e.stopPropagation();
        setQuestions([...questions.slice(0, i - 1), questions[i], questions[i-1], ...questions.slice(i + 1, questions.length)]);
        setActive(active - 1);
    });

    const select = curry((i, e) => {
        e.stopPropagation();
        setActive(i);
    });

    const postQuestions = curry((url, _) => lazyPost(url, { templateId: 1, questions }));


    const renderMenuButtons = (index = 0) => ['TEXT','NUMBER','STRING','FLOAT'].map(type => html`
        <button class="buttonbuttonbuttonbutton" type="button" onClick=${add(index, type)}>${type}</button>
    `);

    const maybeRenderQuestions = useMemo(() => {
        if (!questions) return;

        return html`
            ${questions.map((question, index) => html`
                <${Question}
                    index=${index} 
                    question=${questions[index]}
                    active=${index === active}
                    onClick=${select(index)}
                    onUpdate=${update(index)}
                    edit=${edit}
                    ...${question} 
                }>
                ${ edit ? html`
                    <button type="button" onClick=${moveUp(index)}> <${up}/> </button>
                    <button type="button" onClick=${moveDown(index)}> <${down}/> </button>
                ` : null}
                </${Question}>
                ${ index === active ? html`
                    <${Toggle} class="FloatMenu" onSave=${postQuestions("/api/questions")} text="New Question">
                        ${renderMenuButtons(index)}
                    </${Toggle}> 
                ` : null}
            `)}
        `
    }, [questions, active, questions?.length])

    return html`
        <div class="TemplateForm col-md-8 offset-md-2 g-0">
            <form>
            <h1 class="display-6">Project Completion Report</h1>
                <ul ref=${container} class="list-unstyled">
                    ${maybeRenderQuestions}
                </ul>
            </form>
            ${ (!questions || active !== questions.length - 1) ? html`
                <${Toggle} class="FloatMenu" onSave=${postQuestions("/api/questions")} text="New Question">
                    ${renderMenuButtons(questions?.length || 0)}
                </${Toggle}> 
            ` : null}
        </div>
    `;
}

export default Form;