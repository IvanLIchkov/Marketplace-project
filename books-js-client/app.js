

let loadBooksBtn = document.getElementById("loadBooks");
let tBody = document.getElementById('books-container');

loadBooksBtn.addEventListener("click", reloadBooks)
 async function reloadBooks() {
  let requestOptions = {
    method: 'GET'
  }

  fetch("http://localhost:8080/api/books", requestOptions)
      .then(response => response.json())
      .then(json => json.forEach(book => {
          let tr = createElement('tr', tBody);
          let title = book.title;
          let author = book.author.name;
          let isbn = book.isbn;

          createElement('td',tr,title);
          createElement('td',tr,author);
          createElement('td',tr,isbn);
      }));
}
function createElement(type, parentNode, content, classes, id, attributes, useInnerHtml) {
    const htmlElement = document.createElement(type);

    if (content && useInnerHtml) {
        htmlElement.innerHTML = content;
    } else {
        if (content && type !== 'input') {
            htmlElement.textContent = content;
        }

        if (content && type === 'input') {
            htmlElement.value = content;
        }
    }

    if (classes && classes.length > 0) {
        htmlElement.classList.add(...classes);
    }

    if (id) {
        htmlElement.id = id;
    }

    // { src: 'link', href: 'http' }
    if (attributes) {
        for (const key in attributes) {
            htmlElement.setAttribute(key, attributes[key])
        }
    }

    if (parentNode) {
        parentNode.appendChild(htmlElement);
    }

    return htmlElement;
}
