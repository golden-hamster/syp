document.addEventListener('DOMContentLoaded', function() {
    fetch('http://localhost:8080/api/articles')
        .then(response => response.json())
        .then(data => {
            const articlesDiv = document.getElementById('articles');
            data.articleResponses.content.forEach(article => {
                const articleElement = document.createElement('div');
                articleElement.innerHTML = `<h2>${article.title}</h2><p>${article.content}</p>`;
                articlesDiv.appendChild(articleElement);
            });
        })
        .catch(error => console.error('Error:', error));
});