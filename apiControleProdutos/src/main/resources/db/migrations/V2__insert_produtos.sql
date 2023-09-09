-- Inserir um produto com preço 25.50, estoque mínimo 10 e ativo
INSERT INTO produtos (hash, nome, descricao, ean13, preco, quantidade, estoque_min, dtcreate, dtupdate, l_ativo)
VALUES ('a12b34c5-6789-0123-d45e-67890f123456', 'Produto A', 'Descrição do Produto A', '1234567890123', 25.50, 100, 10,
        current_timestamp, current_timestamp, true);

-- Inserir outro produto com preço 19.99, estoque mínimo 5 e ativo
INSERT INTO produtos (hash, nome, descricao, ean13, preco, quantidade, estoque_min, dtcreate, dtupdate, l_ativo)
VALUES ('3f3e87ce-3a1d-4a4f-92e2-7f1531e2e8a9', 'Produto B', 'Descrição do Produto B', '9876543210987', 19.99, 50, 5,
        current_timestamp, current_timestamp, true);

-- Inserir mais um produto com preço 5.75, estoque mínimo 2 e inativo
INSERT INTO produtos (hash, nome, descricao, ean13, preco, quantidade, estoque_min, dtcreate, dtupdate, l_ativo)
VALUES ('a91b8d54-6e95-4f67-ae1a-826f482c7bbd', 'Produto C', 'Descrição do Produto C', '5432109876543', 5.75, 75, 2,
        current_timestamp, current_timestamp, false);