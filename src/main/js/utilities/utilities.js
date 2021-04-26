export const curry = (fn, arity = fn.length) => (...args) => args.length >= arity ? fn(...args) : (...moreArgs) => curry(fn)(...args, ...moreArgs);
export const compose = (...fns) => (...args) => fns.reduceRight((acc, fn) => [fn.call(null, ...acc)], args)[0];

export const stoprop = (e) => {
    e.stopPropagation(); 
    return e;
};

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

export const lazyPost = (url, body) => {
    return fetch(url, {
        'method': 'POST',
        "headers" : {
            'Content-Type': 'application/json',
            'X-XSRF-TOKEN': getCookie('XSRF-TOKEN')
        },
        "body": JSON.stringify(body)
    });
}