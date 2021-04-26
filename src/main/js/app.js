import { html, render } from 'htm/preact/standalone.mjs'
import Form from './components/Form.js'
import "regenerator-runtime/runtime";
import './style.css'

function App () {

    return html`
        <div class="container-lg mb-5">
            <div class="row">
                <${Form} edit=${true} />  
            </div>
        </div>
    `;
}

render(html`<${App} />`, document.getElementById("here"));