document.addEventListener('DOMContentLoaded', function (){

    const articleId = window.location.pathname.split('/').pop();

    // 게시글 상세 정보 가져오기
    fetch(`http://localhost:8080/api/articles/${articleId}`)
        .then(response => response.json())
        .then(article => {
            const articleTitle = document.getElementById('article-title')
            const articleContent = document.getElementById('article-container')
            articleContent.innerHTML = `
                <section class="col-md-3 col-lg-4 order-md-last">
                   <aside>
                     <p><span id="nickname">${article.createdBy}</span></p>
                     <p><time datetime="2024-04-20T00:00:00">${article.createdAt}</time></p>
                     <p><span id="hashtag" class="badge text-bg-secondary mx-1"><a class="text-reset">#java</a></span></p>
                   </aside>
                </section>

                <article id="article-content" class="col-md-9 col-lg-8">
                    <pre>${article.content}</pre>
                </article>
            `;

            articleTitle.innerHTML = `<h1>${article.title}</h1>`;
        });

    // 댓글 데이터 가져오기
    fetch(`http://localhost:8080/api/articles/${articleId}/comments`)
        .then(response => response.json())
        .then(data => {
            const commentContainer = document.getElementById('article-comments');
            data.comments.forEach(comment => {
                const commentElement = document.createElement('li');
                commentElement.innerHTML = `
                    <strong>${comment.createdBy}</strong>
                    <small>
                    <time>${comment.createdAt}</time>
                    </small>
                    <p class="mb-1">
                         ${comment.content}
                    </p>
                `;
                commentContainer.appendChild(commentElement);
            });
        });
});