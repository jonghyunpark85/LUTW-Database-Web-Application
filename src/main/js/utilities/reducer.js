import { useEffect, useState } from 'htm/preact/standalone.mjs'

const useQuestions = (init) => {
    const [questions, setQuestions] = useState(init);

    const getQuestions = async() => {
        const response = await fetch('/api/questions')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Fetch error');
                }
                return response.json();
            }).catch(err => console.log(err));
        
        setQuestions(response);
    }

    useEffect(getQuestions, []);

    return [questions, getQuestions];
}

export { useQuestions };