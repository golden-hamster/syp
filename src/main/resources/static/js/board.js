document.addEventListener('DOMContentLoaded', function (){

    const apiUrl = 'http://localhost:8080/api/articles';

    fetch(apiUrl)
        .then(response => response.json())
        .then(data => {
            const container = document.getElementById('articleContainer');
            data.articleResponses.content.forEach(article => {
                const articleElement = document.createElement('div');
                articleElement.className = 'col mb-5 d-flex justify-content-center';

                articleElement.innerHTML = `
                <a class="card" href="/articles/${article.id}" style="width: 23rem;" >
                    <img src="/images/temp.png" class="card-img-top rounded-5" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">${article.title}</h5>
                        <p class="card-text">${article.content}</p>
                        <p class="card-text">${article.createdAt}</p>
                    </div>
                </a>
                `;

                container.appendChild(articleElement);
            });
        });


    const searchForm = document.getElementById('search-form');

    searchForm.addEventListener('submit', function(event){
        event.preventDefault();

        const searchInput = document.getElementById('search-input').value;

        fetch(`http://localhost:8080/api/articles?search=${searchInput}`)
            .then(response => response.json())
            .then(data => {
                const container = document.getElementById('articleContainer');
                container.innerHTML = ''; // 기존의 게시글 삭제

                data.articleResponses.content.forEach(article => {
                    const articleElement = document.createElement('div');
                    articleElement.className = 'col mb-5 d-flex justify-content-center';

                    articleElement.innerHTML = `
                    <a class="card" href="/articles/${article.id}" style="width: 23rem;" >
                        <img src="/images/temp.png" class="card-img-top rounded-5" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">${article.title}</h5>
                            <p class="card-text">${article.content}</p>
                            <p class="card-text">${article.createdAt}</p>
                        </div>
                    </a>
                    `;

                    container.appendChild(articleElement);
                });
            })
            .catch(error => console.error('검색 오류:', error));
    })
});

