let inputContainers = document.querySelectorAll('.input-box');
inputContainers.forEach( element => {
    let input = element.querySelector("input");

    input.addEventListener('focusout', function() {
        if (input.value.trim() === '') {
            element.classList.remove('is-filled');
        } else {
            element.classList.add('is-filled');
        }
    });
    
});

let textareaContainers = document.querySelectorAll('.textarea-box');
textareaContainers.forEach( element => {
    let textarea = element.querySelector("textarea");

    textarea.addEventListener('focusout', function() {
        if (textarea.value.trim() === '') {
            element.classList.remove('is-filled');
        } else {
            element.classList.add('is-filled');
        }
    });
    
});
