import { html, useRef, useState, useMemo, useEffect } from 'htm/preact/standalone.mjs'

const inputFields = {
    TEXT : html`<textarea class="fake" rows="2" disabled=true />`,
    NUMBER : html`<input type="number" class="fake" disabled />`,
    FLOAT :  html`<input type="number" class="fake" disabled />`,
    STRING : html`<input type="text" class="fake" disabled />`
}

function Question({index, question, onUpdate, edit = false, onClick, ...props }) {
    const promptRef = useRef(null);
    const subtitleRef = useRef(null);
    const pluralRef = useRef(null);

    useEffect(() => {
        // Focus on prompt textbox on click
        if (props.active) {
           promptRef.current.focus();
        }

        // Adjust textbox size to height of content
        if (subtitleRef.current) {
            const height = subtitleRef.current.scrollHeight;
            subtitleRef.current.style.height = height + "px";
        }
    }, [props.active]);

    const updateQuestion = e => {
        const prompt = promptRef?.current?.value;
        const subtitle = subtitleRef?.current?.value;
        const aggregate = pluralRef?.current?.value;

        onUpdate({ prompt, subtitle, aggregate });
    };

    const renderPromptInput = useMemo(() => html`
        <input
            type="text"
            value=${question.prompt}
            onInput=${updateQuestion}
            class="Question-prompt"
            disabled=${!edit || !props.active}
            id=${`${index}.aggregate`}
            ref=${promptRef}
        />
    `, [index, question.prompt, props.active]);

    const maybeRenderSubtitleInput = useMemo(() => html`
        ${(question.subtitle || props.active) && html`
            <div class="Question-extra">
                ${props.active && html`<label>Question subtitle</label>`}
                <textarea
                    value=${question.subtitle}
                    onInput=${updateQuestion}
                    class="Question-subtitle"
                    rows=1
                    placeholder="Question subtitle"
                    ref=${subtitleRef}
                />
            </div>
        `}
    `, [question.subtitle, props.active]);

    const maybeRenderPluralInput = useMemo(() => {
        if (!['NUMBER', 'FLOAT'].includes(props.type)) return;

        return html`
            <label>Plural for summaries</label>
            <input
                type="text"
                value=${question.aggregate}
                ref=${pluralRef}
                onInput=${updateQuestion}
            />
        `
    }, [question.type, question.aggregate])

    

    return html`
        <li key=${index}
            class=${`Question ${edit && props.active ? 'selected' : ''} ${props.id ? 'new' : ''}`}
            onClick=${onClick}
        >
            <div class="Question-fields">
                ${renderPromptInput}
                ${maybeRenderSubtitleInput}
                ${inputFields[props.type]}
            </div>
            <div class="Question-extra toggle ${props.active ? 'active' : ''}">
                ${maybeRenderPluralInput}
            </div> 
            <div class="Question-actions toggle ${props.active ? 'active' : ''}">
                ${props.children}
            </div>  
        </li>
    `
}

export default Question;