import { html, useState, useRef, useEffect } from 'htm/preact/standalone.mjs'
import { compose, stoprop } from '../utilities/utilities.js'

const colorClasses = {
    'success' : 'buttonbutton-success',
    'error'   : 'buttonbutton-fail'
};

function Toggle({text, onSave, active = false,...props}) {
    const [isActive, setActive] = useState(active);
    const [status, setStatus] = useState(null);
    const saveRef = useRef(null);

    useEffect(() => {
        if (!status) return;

        const removeMe = setTimeout(() => setStatus(null), 400);
        return () => {
            clearTimeout(removeMe);
        }
    }, [status])

    const save = async (e) => {
        const result = onSave(e)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Fetch error');
                }
                setStatus('success');
            }).catch(err => {
                console.log(err)
                setStatus('error');
            });
        
        const message = await result.text;
        console.log(message);
    }

    return html`
        <div ...${props}>
            <button type="button" class="buttonbutton ${status ? colorClasses[status] : ''}" ref=${saveRef} onClick=${save}>${status ? status : 'Save Changes'}</button>
            <button type="button" class="buttonbutton buttonbuttonbutton" onClick=${compose(() => setActive(!isActive), stoprop)}>
                ${text}
            </button>
            <div class=${`toggle ${isActive ? 'active' : ''}`}>
                ${props.children}
            </div>
        </div>
    `;
}

export default Toggle;