const apiUrl = 'http://localhost:8080/produtos'; // URL da sua API

// Carregar produtos
async function carregarProdutos() {
    const res = await fetch(apiUrl);
    const produtos = await res.json();
    const tabela = document.getElementById('produtosTabela');
    tabela.innerHTML = '';
    produtos.forEach(p => {
        tabela.innerHTML += `
            <tr>
                <td>${p.id}</td>
                <td>${p.nome}</td>
                <td>${p.preco.toFixed(2)}</td>
                <td>${p.estoque}</td>
                <td>${p.categoria}</td>
                <td>
                    <button onclick="editarProduto(${p.id})">Editar</button>
                    <button onclick="deletarProduto(${p.id})">Deletar</button>
                </td>
            </tr>
        `;
    });
}

// Criar ou atualizar produto
document.getElementById('produtoForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = document.getElementById('produtoId').value;
    const produto = {
        nome: document.getElementById('nome').value,
        preco: parseFloat(document.getElementById('preco').value),
        estoque: parseInt(document.getElementById('estoque').value),
        categoria: document.getElementById('categoria').value
    };

    if (id) {
        await fetch(`${apiUrl}/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(produto)
        });
    } else {
        await fetch(apiUrl, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(produto)
        });
    }

    document.getElementById('produtoForm').reset();
    document.getElementById('produtoId').value = '';
    carregarProdutos();
});

// Editar produto
async function editarProduto(id) {
    const res = await fetch(`${apiUrl}/${id}`);
    const p = await res.json();
    document.getElementById('produtoId').value = p.id;
    document.getElementById('nome').value = p.nome;
    document.getElementById('preco').value = p.preco;
    document.getElementById('estoque').value = p.estoque;
    document.getElementById('categoria').value = p.categoria;
}

// Cancelar edição
document.getElementById('cancelEdit').addEventListener('click', () => {
    document.getElementById('produtoForm').reset();
    document.getElementById('produtoId').value = '';
});

// Deletar produto
async function deletarProduto(id) {
    if (confirm('Tem certeza que deseja deletar este produto?')) {
        await fetch(`${apiUrl}/${id}`, { method: 'DELETE' });
        carregarProdutos();
    }
}

// Carregar produtos ao abrir a página
carregarProdutos();
