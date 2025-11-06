CREATE DATABASE IF NOT EXISTS dbCuidArtrite CHARACTER SET utf8;
USE dbCuidArtrite;

-- ------------------------------
-- Users
-- ------------------------------
CREATE TABLE users (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  username VARCHAR(255),
  email VARCHAR(255),
  age INT,
  gender VARCHAR(45),
  phone VARCHAR(20),
  password_hash VARCHAR(255),
  font_size INT,          -- 1=small, 2=medium, 3=large
  contrast INT,           -- 0=normal, 1=high contrast
  reading_mode TINYINT,   -- 0=light, 1=dark
  allow_data_collection TINYINT,
  PRIMARY KEY (id)
);

-- ------------------------------
-- Comorbidities
-- ------------------------------
CREATE TABLE comorbidity (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45),
  PRIMARY KEY (id)
);

CREATE TABLE user_comorbidity (
  user_id INT NOT NULL,
  comorbidity_id INT NOT NULL,
  PRIMARY KEY (user_id, comorbidity_id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (comorbidity_id) REFERENCES comorbidity(id)
);

-- ------------------------------
-- Pain Assessment
-- ------------------------------
CREATE TABLE pain_assessment (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  pain_scale INT,
  graphic_scale VARCHAR(45),
  localized_pain VARCHAR(45),
  date DATETIME,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

-- ------------------------------
-- Techniques
-- ------------------------------
CREATE TABLE technique_type (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45),
  PRIMARY KEY (id)
);

CREATE TABLE technique (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(255),
  how_long TEXT,
  benefits TEXT,
  how_to_do TEXT,
  hint TEXT,
  video_url VARCHAR(255),
  picture_url VARCHAR(255),
  technique_type_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (technique_type_id) REFERENCES technique_type(id)
);

CREATE TABLE technique_history (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  technique_id INT NOT NULL,
  date DATETIME,
  initial_pain_scale INT,
  final_pain_scale INT,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (technique_id) REFERENCES technique(id)
);

-- ------------------------------
-- Educative content & history
-- ------------------------------
CREATE TABLE educative_content (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(255),
  body TEXT,
  PRIMARY KEY (id)
);

CREATE TABLE educative_history (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  content_id INT NOT NULL,
  visualised TINYINT,
  date DATETIME,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (content_id) REFERENCES educative_content(id)
);

-- ------------------------------
-- Educative content Inserts
-- ------------------------------

INSERT INTO educative_content (title, body) VALUES
('ENTENDENDO SUA CONDIÇÃO',
'O que acontece? A osteoartrite (ou artrose) é o desgaste natural da cartilagem que protege suas articulações. Com o tempo, os ossos ficam mais próximos e causam dor e rigidez. Pense assim: É como o desgaste de um pneu de carro - com o uso ao longo dos anos, a proteção vai diminuindo. IMPORTANTE SABER: - É muito comum após os 60 anos - NÃO é culpa sua - Tem tratamento e controle - Você pode viver bem com osteoartrite. Articulações mais afetadas: - Joelhos - Mãos e dedos - Quadril - Coluna - Pés');

INSERT INTO educative_content (title, body) VALUES
('RECONHECENDO OS SINAIS',
'Sintomas principais: Dor nas articulações (piora com movimento), Rigidez pela manhã (melhora em 30 min), Inchaço leve nas juntas, Estalos ao movimentar, Dificuldade para realizar tarefas simples, Sensação de "travamento". Padrão comum: Manhã mais rígido, Tarde melhora com movimento suave, Noite pode doer após atividades. A dor varia: Alguns dias melhor, outros pior - é normal! QUANDO PROCURAR AJUDA URGENTE: Dor muito forte e súbita, Inchaço grande e vermelhidão, Febre junto com dor, Impossibilidade de mover a articulação.');

INSERT INTO educative_content (title, body) VALUES
('POR QUE ACONTECE?',
'Causas principais: Idade, Uso repetitivo, Lesões anteriores, Sobrepeso, Genética, Postura inadequada. Fatores que você pode controlar: Peso corporal, Atividade física regular, Postura no dia a dia, Proteção das articulações, Alimentação saudável.');

INSERT INTO educative_content (title, body) VALUES
('OPÇÕES DE TRATAMENTO',
'Objetivo: Reduzir dor e manter movimento. Medicamentos: Analgésicos, Anti-inflamatórios (com orientação médica), Pomadas e géis, Suplementos. Práticas integrativas: Exercícios adaptados, Termoterapia, Acupuntura/Acupressão, Yoga e Tai Chi, Massagem terapêutica, Fitoterapia. Fisioterapia: Fortalecimento muscular, Melhora da mobilidade, Técnicas de proteção articular. Mudanças no estilo de vida: Perda de peso (se necessário), Alimentação anti-inflamatória, Exercícios regulares, Adaptações no dia a dia. Tratamentos avançados: Infiltrações, Viscossuplementação, Cirurgia. Abordagem integrada: Melhor resultado vem da combinação de tratamentos.');

INSERT INTO educative_content (title, body) VALUES
('COMENDO PARA ALIVIAR',
'Alimentos amigos: Peixes (salmão, sardinha), Azeite de oliva extra virgem, Vegetais verde-escuros, Frutas vermelhas, Alho e cebola, Gengibre e cúrcuma, Castanhas e nozes, Frutas cítricas. Alimentos a evitar ou reduzir: Açúcar em excesso, Frituras, Carnes processadas, Bebidas alcoólicas, Sal. Hidratação: Beba 6-8 copos de água por dia. Chás recomendados: Chá verde, Gengibre, Cúrcuma, Cavalinha. Sempre consulte seu médico antes de mudanças grandes na dieta.');

INSERT INTO educative_content (title, body) VALUES
('ADAPTAÇÕES PRÁTICAS',
'Na cozinha: Use utensílios com cabos grossos e antiderrapantes, Abridores automáticos, Banqueta para sentar, Organize itens na altura dos olhos. No banheiro: Barras de apoio no box, Tapete antiderrapante, Banco para sentar no banho, Assento elevado no vaso sanitário. No quarto: Colchão firme, Travesseiro adequado, Alças para ajudar a levantar, Luz de presença. Calçados: Solado antiderrapante, Salto baixo, Amortecimento, Fechamento fácil. Movimentação: Use bengala ou andador se necessário, Levante-se devagar, Evite ficar muito tempo na mesma posição, Faça pausas em atividades longas. Proteção articular: Carregue peso perto do corpo, Use carrinho para compras, Empurre portas com o corpo, Use as duas mãos para tarefas pesadas.');

INSERT INTO educative_content (title, body) VALUES
('CUIDANDO DA MENTE',
'É normal sentir frustração com limitações, Medo do futuro, Tristeza em dias de dor, Ansiedade sobre a progressão. Você não está sozinho: Milhões de pessoas têm osteoartrite, é possível viver bem. Estratégias de enfrentamento: Foque no que você pode fazer, Celebre pequenas vitórias, Mantenha hobbies e vida social, Peça ajuda quando precisar, Participe de grupos de apoio, Pratique gratidão diariamente. Atividades que ajudam: Meditação e relaxamento, Conexão com amigos/família, Atividades prazerosas, Voluntariado, Aprender coisas novas. Busque ajuda se sentir tristeza constante, Perda de interesse em tudo, Isolamento social, Pensamentos negativos frequentes. Psicólogo pode ajudar muito!');

INSERT INTO educative_content (title, body) VALUES
('SINAIS DE ALERTA',
'Marque consulta se: Dor não melhora com tratamento atual, Dificuldade crescente para atividades diárias, Efeitos colaterais de medicamentos, Dúvidas sobre tratamento, Piora progressiva dos sintomas. Procure urgência se: Dor súbita e muito intensa, Inchaço grande e vermelhidão, Febre com dor articular, Incapacidade total de movimento, Deformidade visível repentina. Consultas regulares: Acompanhamento periódico, Ajuste de medicações, Avaliação da progressão, Discussão de novas opções. Prepare sua consulta: Liste sintomas, Anote dúvidas, Leve lista de medicamentos, Registre o que melhora/piora, Mencione tratamentos que tentou.');

INSERT INTO educative_content (title, body) VALUES
('QUALIDADE DE VIDA',
'Princípios fundamentais: Movimento é remédio, Equilíbrio entre atividade e descanso, Atitude positiva faz diferença, Apoio social é importante, Aprendizado contínuo empodera. Estabeleça metas realistas: Pequenos passos, Celebre progressos, Ajuste quando necessário, Seja gentil consigo mesmo. Acompanhe sua evolução: Use este app, Registre dor e atividades, Note padrões, Compartilhe com médico. Você é protagonista: Suas escolhas fazem diferença, Conhecimento é poder, Autocuidado é fundamental. Lembre-se: "Osteoartrite é parte de você, mas não define quem você é!"');

-- ------------------------------
-- technique type Inserts
-- ------------------------------
INSERT INTO technique_type (name) VALUES ('Técnicas de alívio'); -- id 1

INSERT INTO technique_type (name) VALUES ('Alongamentos guiados'); -- id 2

INSERT INTO technique_type (name) VALUES ('Técnicas de respiração'); -- id 3

-- ------------------------------
-- techniques Inserts
-- ------------------------------

INSERT INTO technique (title, how_long, benefits, how_to_do, hint, technique_type_id) VALUES
('RESPIRAÇÃO PROFUNDA', '5 minutos', 'Reduz tensão e ansiedade', '1. Sente-se confortavelmente ou deite 2. Coloque uma mão na barriga 3. Inspire pelo nariz contando até 4 4. Sinta a barriga subir (não o peito) 5. Expire pela boca contando até 6 6. Repita 10 vezes', 'Faça antes de dormir', 3);

INSERT INTO technique (title, how_long, benefits, how_to_do, hint, technique_type_id) VALUES
('RESPIRAÇÃO 4-7-8', '3-5 minutos', 'Acalma e melhora o sono', '1. Inspire pelo nariz: conte até 4 2. Segure o ar: conte até 7 3. Expire pela boca: conte até 8 4. Faça 4 ciclos completos', 'Pode dar leve tontura no início - é normal', 3);

INSERT INTO technique (title, how_long, benefits, how_to_do, hint, technique_type_id) VALUES
('SUSPIRO DE ALÍVIO', '2 minutos', 'Libera tensão rápida', '1. Inspire profundamente pelo nariz 2. Solte o ar pela boca com um suspiro sonoro 3. Deixe os ombros caírem 4. Repita 5 vezes "Aaaah..." - solte o som!', NULL, 3);

INSERT INTO technique (title, how_long, benefits, how_to_do, hint, technique_type_id) VALUES
('RELAXAMENTO MUSCULAR', '10-15 minutos', 'Alivia tensão e dor muscular', '1. Deite-se confortavelmente 2. Comece pelos pés: - Contraia os músculos por 5 segundos - Relaxe completamente por 10 segundos 3. Suba pelo corpo: - Panturrilhas - Coxas - Barriga - Mãos e braços - Ombros - Rosto', 'Não force articulações doloridas [Áudio guiado disponível]', 3);

INSERT INTO technique (title, how_long, benefits, how_to_do, hint, technique_type_id) VALUES
('TOQUE CALMANTE', '5 minutos', 'Conforto imediato', '1. Esfregue as mãos até aquecer 2. Coloque as mãos nos locais doloridos 3. Faça movimentos circulares suaves 4. Respire profundamente 5. Imagine o calor aliviando a dor', 'Pode usar óleo morno', 3);

-- ------------------------------
-- Users Inserts
-- ------------------------------
INSERT INTO users (name, username, email, age, gender, phone, password_hash, font_size, contrast, reading_mode, allow_data_collection)
VALUES
('Marcos', 'Marcos101', 'marcos_olimpia@yahoo.com.br', 27, 'Masculino', '51999999999', 'hash123', 2, 0, 0, 1),
('Maria','Maria10','maria_oliveira@yahoo.com.br', 65, 'Feminino', '51988888888', 'hash456', 2, 1, 1, 1);

-- ------------------------------
-- Pain Assessment Inserts
-- ------------------------------
INSERT INTO pain_assessment (user_id, pain_scale, graphic_scale, localized_pain, date)
VALUES
(1, 3, 'Moderado', 'Joelhos', '2025-10-25 10:00:00'),
(1, 4, 'Forte', 'Coluna', '2025-10-26 15:30:00'),
(2, 5, 'Muito forte', 'Mãos e dedos', '2025-10-25 09:00:00');

-- ------------------------------
-- Technique History Inserts
-- ------------------------------
INSERT INTO technique_history (user_id, technique_id, date, initial_pain_scale, final_pain_scale)
VALUES
(1, 1, '2025-10-25 10:15:00', 3, 2),   -- Fernando did RESPIRAÇÃO PROFUNDA
(1, 4, '2025-10-26 16:00:00', 4, 2),   -- Fernando did RELAXAMENTO MUSCULAR
(2, 2, '2025-10-25 09:15:00', 5, 3);   -- Maria did RESPIRAÇÃO 4-7-8

-- ------------------------------
-- Educative History Inserts
-- ------------------------------
INSERT INTO educative_history (user_id, content_id, visualised, date)
VALUES
(1, 1, 1, '2025-10-25 10:05:00'),  -- Fernando viewed "ENTENDENDO SUA CONDIÇÃO"
(1, 4, 1, '2025-10-26 15:45:00'),  -- Fernando viewed "OPÇÕES DE TRATAMENTO"
(2, 2, 1, '2025-10-25 09:05:00');  -- Maria viewed "RECONHECENDO OS SINAIS"