document.addEventListener('DOMContentLoaded', function (){
    fetch('http://localhost:8080/api/articles')
        .then(response => response.json())
        .then(data => {
            const container = document.getElementById('articleContainer');
            data.articleResponses.content.forEach(article => {
                const articleElement = document.createElement('a');
                articleElement.href = '/articles/' + article.id;
                articleElement.className = 'card col-3';
                articleElement.style.width = '18rem';

                articleElement.innerHTML = `
                <img src="/images/temp.png" class="card-img-top rounded-5" alt="...">
                <div class="card-body">
                <h5 class="card-title">${article.title}</h5>
                <p class="card-text">${article.content}</p>
                <p class="card-text">${article.createdAt}</p>
                </div>
                `;

                container.appendChild(articleElement);
            });
        });
});

