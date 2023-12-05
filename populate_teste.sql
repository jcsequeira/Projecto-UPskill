
-- Use the Database
USE explorart;

-- Insert sample data into Cidade Table
INSERT INTO Cidade (Nome_Cidade) VALUES
    ('Lisbon'),
    ('Madrid'),
    ('Paris'),
    ('New York'),
    ('London');

-- Insert sample data into Pais Table
INSERT INTO Pais (Nome_Pais, Nacionalidade) VALUES
    ('Portugal', 'Portuguese'),
    ('Spain', 'Spanish'),
    ('France', 'French'),
    ('United States', 'American Statian'),
    ('United Kingdom', 'British');

-- Insert sample data into Movimento Table
INSERT INTO Movimento (Nome_Movimento) VALUES
    ('Impressionism'),
    ('Cubism'),
    ('Surrealism'),
    ('Modernism'),
    ('Contemporary');

-- Insert sample data into Tecnica Table
INSERT INTO Tecnica (Tipo_Tecnica) VALUES
    ('Oil on Canvas'),
    ('Watercolor'),
    ('Sculpture'),
    ('Mixed Media'),
    ('Digital Art');

-- Insert sample data into Materiais Table
INSERT INTO Materiais (Tipo_Material) VALUES
    ('Oil Paint'),
    ('Canvas'),
    ('Bronze'),
    ('Acrylic Paint'),
    ('Wood');

-- Insert sample data into Colaborador Table
INSERT INTO Colaborador (Nome_Colaborador, Email, Telefone, Codigo_Pais) VALUES
    ('John Doe', 'john.doe@email.com', '123-456-7890', 4),
    ('Jane Smith', 'jane.smith@email.com', '987-654-3210', 1),
    ('Carlos Rodriguez', 'carlos.rodriguez@email.com', '555-123-4567', 2),
    ('Maria Garcia', 'maria.garcia@email.com', '111-222-3333', 3),
    ('David Brown', 'david.brown@email.com', '999-888-7777', 5);

-- Insert sample data into Administrador Table
INSERT INTO Administrador (password, id_colaborador) VALUES
    ('adminpass', 1);

-- Insert sample data into Galerista Table
INSERT INTO Galerista (Data_Inicio_Atividade, password, id_colaborador) VALUES
    ('2022-01-01', 'passw0rd', 2);

-- Insert sample data into Artista Table
INSERT INTO Artista (nome_artista, Data_Nascimento, Biografia, Data_Morte, Nacionalidade) VALUES
    ('Pablo Picasso', '1980-05-10', 'Renowned contemporary artist', NULL, 'Portuguese'),
    ('Vinvent Van Gogh', '1965-02-20', 'Sculptor specializing in bronze sculptures', NULL, 'Spanish'),
    ('Frida Khalo', '1978-09-15', 'Digital artist exploring virtual realities', NULL, 'French');

-- Insert sample data into Obra_Arte Table
INSERT INTO Obra_Arte (Titulo, Link_Imagem, Ano_Criacao, Preco, altura, Largura, Profundidade, Diametro, IsActive, id_artista, id_Tecnica, id_Estilo) VALUES
    ('Cityscape', 'image_link1.jpg', '2022-01-15', 5000.00, 80.1 ,120.0, 80.0, NULL, 1, 1, 1, 5),
    ('Sculpture of Freedom', 'image_link2.jpg', '2022-03-20', 15000.00, 120.5, NULL, NULL, NULL, 1, 2, 3, 4),
    ('Virtual World', 'image_link3.jpg', '2022-05-12', 8000.00, 55, NULL, NULL, NULL, 1, 3, 5, 5);

-- Insert sample data into Galeria Table
INSERT INTO Galeria (Nome_Galeria, Morada, Website, Email, Telefone, id_Cidade, id_colaborador) VALUES
    ('Artistic Hub', '123 Main St, Lisbon', 'www.artistichub.com', 'info@artistichub.com', '555-111-2222', 1, 2),
    ('Modern Impressions', '456 Art Ave, Madrid', 'www.modernimpressions.com', 'info@modernimpressions.com', '666-333-4444', 2, 2),
    ('Surreal Spaces', '789 Dream St, Paris', 'www.surrealspaces.com', 'info@surrealspaces.com', '777-999-8888', 3, 2);

-- Insert sample data into Evento Table
INSERT INTO Evento (Nome, Data_inicio, Data_Fim, Descricao, id_Galeria) VALUES
    ('Contemporary Showcase', '2022-06-01', '2022-06-30', 'A collection of the latest contemporary artworks.', 1),
    ('Bronze Beauty', '2022-08-15', '2022-09-15', 'An exhibition featuring stunning bronze sculptures.', 2),
    ('Virtual Realities', '2022-10-01', '2022-10-31', 'Explore the digital world through immersive art.', 3);

-- Insert sample data into Emprestimo_Obra_Galeria Table
INSERT INTO Emprestimo_Obra_Galeria (Data_Entrada, Data_Saida, id_Galeria, id_Obra_Arte) VALUES
    ('2022-06-01', '2022-06-15', 1, 1),
    ('2022-08-20', '2022-09-05', 2, 2),
    ('2022-10-10', '2022-10-25', 3, 3);

-- Insert sample data into Obra_Exposicao Table
INSERT INTO Obra_Exposicao (id_Obra_Arte, id_Expo) VALUES
    (1, 1),
    (2, 2),
    (3, 3);

-- Insert sample data into Obra_Materiais Table
INSERT INTO Obra_Materiais (id_Material, id_Obra_Arte) VALUES
    (1, 1),
    (3, 2),
    (5, 3);
