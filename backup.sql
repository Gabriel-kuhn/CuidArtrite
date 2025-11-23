-- MySQL dump 10.13  Distrib 8.0.44, for Linux (x86_64)
--
-- Host: localhost    Database: dbCuidArtrite
-- ------------------------------------------------------
-- Server version	8.0.44-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comorbidity`
--

DROP TABLE IF EXISTS `comorbidity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comorbidity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comorbidity`
--

LOCK TABLES `comorbidity` WRITE;
/*!40000 ALTER TABLE `comorbidity` DISABLE KEYS */;
/*!40000 ALTER TABLE `comorbidity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `educative_content`
--

DROP TABLE IF EXISTS `educative_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `educative_content` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `body` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `educative_content`
--

LOCK TABLES `educative_content` WRITE;
/*!40000 ALTER TABLE `educative_content` DISABLE KEYS */;
INSERT INTO `educative_content` VALUES (1,'ENTENDENDO SUA CONDIÇÃO','O que acontece? A osteoartrite (ou artrose) é o desgaste natural da cartilagem que protege suas articulações. Com o tempo, os ossos ficam mais próximos e causam dor e rigidez. Pense assim: É como o desgaste de um pneu de carro - com o uso ao longo dos anos, a proteção vai diminuindo. IMPORTANTE SABER: - É muito comum após os 60 anos - NÃO é culpa sua - Tem tratamento e controle - Você pode viver bem com osteoartrite. Articulações mais afetadas: - Joelhos - Mãos e dedos - Quadril - Coluna - Pés'),(2,'RECONHECENDO OS SINAIS','Sintomas principais: Dor nas articulações (piora com movimento), Rigidez pela manhã (melhora em 30 min), Inchaço leve nas juntas, Estalos ao movimentar, Dificuldade para realizar tarefas simples, Sensação de \"travamento\". Padrão comum: Manhã mais rígido, Tarde melhora com movimento suave, Noite pode doer após atividades. A dor varia: Alguns dias melhor, outros pior - é normal! QUANDO PROCURAR AJUDA URGENTE: Dor muito forte e súbita, Inchaço grande e vermelhidão, Febre junto com dor, Impossibilidade de mover a articulação.'),(3,'POR QUE ACONTECE?','Causas principais: Idade, Uso repetitivo, Lesões anteriores, Sobrepeso, Genética, Postura inadequada. Fatores que você pode controlar: Peso corporal, Atividade física regular, Postura no dia a dia, Proteção das articulações, Alimentação saudável.'),(4,'OPÇÕES DE TRATAMENTO','Objetivo: Reduzir dor e manter movimento. Medicamentos: Analgésicos, Anti-inflamatórios (com orientação médica), Pomadas e géis, Suplementos. Práticas integrativas: Exercícios adaptados, Termoterapia, Acupuntura/Acupressão, Yoga e Tai Chi, Massagem terapêutica, Fitoterapia. Fisioterapia: Fortalecimento muscular, Melhora da mobilidade, Técnicas de proteção articular. Mudanças no estilo de vida: Perda de peso (se necessário), Alimentação anti-inflamatória, Exercícios regulares, Adaptações no dia a dia. Tratamentos avançados: Infiltrações, Viscossuplementação, Cirurgia. Abordagem integrada: Melhor resultado vem da combinação de tratamentos.'),(5,'COMENDO PARA ALIVIAR','Alimentos amigos: Peixes (salmão, sardinha), Azeite de oliva extra virgem, Vegetais verde-escuros, Frutas vermelhas, Alho e cebola, Gengibre e cúrcuma, Castanhas e nozes, Frutas cítricas. Alimentos a evitar ou reduzir: Açúcar em excesso, Frituras, Carnes processadas, Bebidas alcoólicas, Sal. Hidratação: Beba 6-8 copos de água por dia. Chás recomendados: Chá verde, Gengibre, Cúrcuma, Cavalinha. Sempre consulte seu médico antes de mudanças grandes na dieta.'),(6,'ADAPTAÇÕES PRÁTICAS','Na cozinha: Use utensílios com cabos grossos e antiderrapantes, Abridores automáticos, Banqueta para sentar, Organize itens na altura dos olhos. No banheiro: Barras de apoio no box, Tapete antiderrapante, Banco para sentar no banho, Assento elevado no vaso sanitário. No quarto: Colchão firme, Travesseiro adequado, Alças para ajudar a levantar, Luz de presença. Calçados: Solado antiderrapante, Salto baixo, Amortecimento, Fechamento fácil. Movimentação: Use bengala ou andador se necessário, Levante-se devagar, Evite ficar muito tempo na mesma posição, Faça pausas em atividades longas. Proteção articular: Carregue peso perto do corpo, Use carrinho para compras, Empurre portas com o corpo, Use as duas mãos para tarefas pesadas.'),(7,'CUIDANDO DA MENTE','É normal sentir frustração com limitações, Medo do futuro, Tristeza em dias de dor, Ansiedade sobre a progressão. Você não está sozinho: Milhões de pessoas têm osteoartrite, é possível viver bem. Estratégias de enfrentamento: Foque no que você pode fazer, Celebre pequenas vitórias, Mantenha hobbies e vida social, Peça ajuda quando precisar, Participe de grupos de apoio, Pratique gratidão diariamente. Atividades que ajudam: Meditação e relaxamento, Conexão com amigos/família, Atividades prazerosas, Voluntariado, Aprender coisas novas. Busque ajuda se sentir tristeza constante, Perda de interesse em tudo, Isolamento social, Pensamentos negativos frequentes. Psicólogo pode ajudar muito!'),(8,'SINAIS DE ALERTA','Marque consulta se: Dor não melhora com tratamento atual, Dificuldade crescente para atividades diárias, Efeitos colaterais de medicamentos, Dúvidas sobre tratamento, Piora progressiva dos sintomas. Procure urgência se: Dor súbita e muito intensa, Inchaço grande e vermelhidão, Febre com dor articular, Incapacidade total de movimento, Deformidade visível repentina. Consultas regulares: Acompanhamento periódico, Ajuste de medicações, Avaliação da progressão, Discussão de novas opções. Prepare sua consulta: Liste sintomas, Anote dúvidas, Leve lista de medicamentos, Registre o que melhora/piora, Mencione tratamentos que tentou.'),(9,'QUALIDADE DE VIDA','Princípios fundamentais: Movimento é remédio, Equilíbrio entre atividade e descanso, Atitude positiva faz diferença, Apoio social é importante, Aprendizado contínuo empodera. Estabeleça metas realistas: Pequenos passos, Celebre progressos, Ajuste quando necessário, Seja gentil consigo mesmo. Acompanhe sua evolução: Use este app, Registre dor e atividades, Note padrões, Compartilhe com médico. Você é protagonista: Suas escolhas fazem diferença, Conhecimento é poder, Autocuidado é fundamental. Lembre-se: \"Osteoartrite é parte de você, mas não define quem você é!\"'),(10,'ENTENDENDO SUA CONDIÇÃO','O que acontece? A osteoartrite (ou artrose) é o desgaste natural da cartilagem que protege suas articulações. Com o tempo, os ossos ficam mais próximos e causam dor e rigidez. Pense assim: É como o desgaste de um pneu de carro - com o uso ao longo dos anos, a proteção vai diminuindo. IMPORTANTE SABER: - É muito comum após os 60 anos - NÃO é culpa sua - Tem tratamento e controle - Você pode viver bem com osteoartrite. Articulações mais afetadas: - Joelhos - Mãos e dedos - Quadril - Coluna - Pés'),(11,'RECONHECENDO OS SINAIS','Sintomas principais: Dor nas articulações (piora com movimento), Rigidez pela manhã (melhora em 30 min), Inchaço leve nas juntas, Estalos ao movimentar, Dificuldade para realizar tarefas simples, Sensação de \"travamento\". Padrão comum: Manhã mais rígido, Tarde melhora com movimento suave, Noite pode doer após atividades. A dor varia: Alguns dias melhor, outros pior - é normal! QUANDO PROCURAR AJUDA URGENTE: Dor muito forte e súbita, Inchaço grande e vermelhidão, Febre junto com dor, Impossibilidade de mover a articulação.'),(12,'POR QUE ACONTECE?','Causas principais: Idade, Uso repetitivo, Lesões anteriores, Sobrepeso, Genética, Postura inadequada. Fatores que você pode controlar: Peso corporal, Atividade física regular, Postura no dia a dia, Proteção das articulações, Alimentação saudável.'),(13,'OPÇÕES DE TRATAMENTO','Objetivo: Reduzir dor e manter movimento. Medicamentos: Analgésicos, Anti-inflamatórios (com orientação médica), Pomadas e géis, Suplementos. Práticas integrativas: Exercícios adaptados, Termoterapia, Acupuntura/Acupressão, Yoga e Tai Chi, Massagem terapêutica, Fitoterapia. Fisioterapia: Fortalecimento muscular, Melhora da mobilidade, Técnicas de proteção articular. Mudanças no estilo de vida: Perda de peso (se necessário), Alimentação anti-inflamatória, Exercícios regulares, Adaptações no dia a dia. Tratamentos avançados: Infiltrações, Viscossuplementação, Cirurgia. Abordagem integrada: Melhor resultado vem da combinação de tratamentos.'),(14,'COMENDO PARA ALIVIAR','Alimentos amigos: Peixes (salmão, sardinha), Azeite de oliva extra virgem, Vegetais verde-escuros, Frutas vermelhas, Alho e cebola, Gengibre e cúrcuma, Castanhas e nozes, Frutas cítricas. Alimentos a evitar ou reduzir: Açúcar em excesso, Frituras, Carnes processadas, Bebidas alcoólicas, Sal. Hidratação: Beba 6-8 copos de água por dia. Chás recomendados: Chá verde, Gengibre, Cúrcuma, Cavalinha. Sempre consulte seu médico antes de mudanças grandes na dieta.'),(15,'ADAPTAÇÕES PRÁTICAS','Na cozinha: Use utensílios com cabos grossos e antiderrapantes, Abridores automáticos, Banqueta para sentar, Organize itens na altura dos olhos. No banheiro: Barras de apoio no box, Tapete antiderrapante, Banco para sentar no banho, Assento elevado no vaso sanitário. No quarto: Colchão firme, Travesseiro adequado, Alças para ajudar a levantar, Luz de presença. Calçados: Solado antiderrapante, Salto baixo, Amortecimento, Fechamento fácil. Movimentação: Use bengala ou andador se necessário, Levante-se devagar, Evite ficar muito tempo na mesma posição, Faça pausas em atividades longas. Proteção articular: Carregue peso perto do corpo, Use carrinho para compras, Empurre portas com o corpo, Use as duas mãos para tarefas pesadas.'),(16,'CUIDANDO DA MENTE','É normal sentir frustração com limitações, Medo do futuro, Tristeza em dias de dor, Ansiedade sobre a progressão. Você não está sozinho: Milhões de pessoas têm osteoartrite, é possível viver bem. Estratégias de enfrentamento: Foque no que você pode fazer, Celebre pequenas vitórias, Mantenha hobbies e vida social, Peça ajuda quando precisar, Participe de grupos de apoio, Pratique gratidão diariamente. Atividades que ajudam: Meditação e relaxamento, Conexão com amigos/família, Atividades prazerosas, Voluntariado, Aprender coisas novas. Busque ajuda se sentir tristeza constante, Perda de interesse em tudo, Isolamento social, Pensamentos negativos frequentes. Psicólogo pode ajudar muito!'),(17,'SINAIS DE ALERTA','Marque consulta se: Dor não melhora com tratamento atual, Dificuldade crescente para atividades diárias, Efeitos colaterais de medicamentos, Dúvidas sobre tratamento, Piora progressiva dos sintomas. Procure urgência se: Dor súbita e muito intensa, Inchaço grande e vermelhidão, Febre com dor articular, Incapacidade total de movimento, Deformidade visível repentina. Consultas regulares: Acompanhamento periódico, Ajuste de medicações, Avaliação da progressão, Discussão de novas opções. Prepare sua consulta: Liste sintomas, Anote dúvidas, Leve lista de medicamentos, Registre o que melhora/piora, Mencione tratamentos que tentou.'),(18,'QUALIDADE DE VIDA','Princípios fundamentais: Movimento é remédio, Equilíbrio entre atividade e descanso, Atitude positiva faz diferença, Apoio social é importante, Aprendizado contínuo empodera. Estabeleça metas realistas: Pequenos passos, Celebre progressos, Ajuste quando necessário, Seja gentil consigo mesmo. Acompanhe sua evolução: Use este app, Registre dor e atividades, Note padrões, Compartilhe com médico. Você é protagonista: Suas escolhas fazem diferença, Conhecimento é poder, Autocuidado é fundamental. Lembre-se: \"Osteoartrite é parte de você, mas não define quem você é!\"');
/*!40000 ALTER TABLE `educative_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `educative_history`
--

DROP TABLE IF EXISTS `educative_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `educative_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `content_id` int NOT NULL,
  `visualised` tinyint DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `content_id` (`content_id`),
  CONSTRAINT `educative_history_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `educative_history_ibfk_2` FOREIGN KEY (`content_id`) REFERENCES `educative_content` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `educative_history`
--

LOCK TABLES `educative_history` WRITE;
/*!40000 ALTER TABLE `educative_history` DISABLE KEYS */;
INSERT INTO `educative_history` VALUES (1,1,1,1,'2025-10-25 10:05:00'),(2,1,4,1,'2025-10-26 15:45:00'),(3,2,2,1,'2025-10-25 09:05:00'),(4,1,1,1,'2025-10-25 10:05:00'),(5,1,4,1,'2025-10-26 15:45:00'),(6,2,2,1,'2025-10-25 09:05:00');
/*!40000 ALTER TABLE `educative_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pain_assessment`
--

DROP TABLE IF EXISTS `pain_assessment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pain_assessment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `pain_scale` int DEFAULT NULL,
  `graphic_scale` varchar(45) DEFAULT NULL,
  `localized_pain` varchar(45) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `pain_assessment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pain_assessment`
--

LOCK TABLES `pain_assessment` WRITE;
/*!40000 ALTER TABLE `pain_assessment` DISABLE KEYS */;
INSERT INTO `pain_assessment` VALUES (1,1,3,'Moderado','Joelhos','2025-10-25 10:00:00'),(2,1,4,'Forte','Coluna','2025-10-26 15:30:00'),(3,2,5,'Muito forte','Mãos e dedos','2025-10-25 09:00:00'),(4,1,4,NULL,'Coluna','2025-11-07 11:39:06'),(5,1,4,NULL,'Joelhos','2025-11-04 08:00:00'),(6,1,3,NULL,'Mãos','2025-11-05 08:30:00'),(7,1,5,NULL,'Coluna','2025-11-06 09:00:00'),(8,1,3,NULL,'Joelhos','2025-11-07 08:15:00'),(9,1,2,NULL,'Ombros','2025-11-08 07:45:00'),(10,1,4,NULL,'Joelhos e Coluna','2025-11-09 10:00:00'),(11,1,3,NULL,'Mãos e Punhos','2025-11-10 09:30:00'),(12,2,5,NULL,'Mãos e dedos','2025-10-25 09:00:00'),(13,2,4,NULL,'Punhos','2025-11-05 10:00:00'),(14,1,3,NULL,'Joelhos','2025-10-25 10:00:00'),(15,1,4,NULL,'Coluna','2025-10-26 15:30:00'),(16,2,5,NULL,'Mãos e dedos','2025-10-25 09:00:00');
/*!40000 ALTER TABLE `pain_assessment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technique`
--

DROP TABLE IF EXISTS `technique`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `technique` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `how_long` text,
  `benefits` text,
  `how_to_do` text,
  `hint` text,
  `video_url` varchar(255) DEFAULT NULL,
  `picture_url` varchar(255) DEFAULT NULL,
  `technique_type_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `technique_type_id` (`technique_type_id`),
  CONSTRAINT `technique_ibfk_1` FOREIGN KEY (`technique_type_id`) REFERENCES `technique_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `technique`
--

LOCK TABLES `technique` WRITE;
/*!40000 ALTER TABLE `technique` DISABLE KEYS */;
INSERT INTO `technique` VALUES (1,'RESPIRAÇÃO PROFUNDA','5 minutos','Reduz tensão e ansiedade','1. Sente-se confortavelmente ou deite 2. Coloque uma mão na barriga 3. Inspire pelo nariz contando até 4 4. Sinta a barriga subir (não o peito) 5. Expire pela boca contando até 6 6. Repita 10 vezes','Faça antes de dormir',NULL,NULL,3),(2,'RESPIRAÇÃO 4-7-8','3-5 minutos','Acalma e melhora o sono','1. Inspire pelo nariz: conte até 4 2. Segure o ar: conte até 7 3. Expire pela boca: conte até 8 4. Faça 4 ciclos completos','Pode dar leve tontura no início - é normal',NULL,NULL,3),(3,'SUSPIRO DE ALÍVIO','2 minutos','Libera tensão rápida','1. Inspire profundamente pelo nariz 2. Solte o ar pela boca com um suspiro sonoro 3. Deixe os ombros caírem 4. Repita 5 vezes \"Aaaah...\" - solte o som!',NULL,NULL,NULL,3),(4,'RELAXAMENTO MUSCULAR','10-15 minutos','Alivia tensão e dor muscular','1. Deite-se confortavelmente 2. Comece pelos pés: - Contraia os músculos por 5 segundos - Relaxe completamente por 10 segundos 3. Suba pelo corpo: - Panturrilhas - Coxas - Barriga - Mãos e braços - Ombros - Rosto','Não force articulações doloridas [Áudio guiado disponível]',NULL,NULL,3),(5,'TOQUE CALMANTE','5 minutos','Conforto imediato','1. Esfregue as mãos até aquecer 2. Coloque as mãos nos locais doloridos 3. Faça movimentos circulares suaves 4. Respire profundamente 5. Imagine o calor aliviando a dor','Pode usar óleo morno',NULL,NULL,3),(6,'RESPIRAÇÃO PROFUNDA','5 minutos','Reduz tensão e ansiedade','1. Sente-se confortavelmente ou deite 2. Coloque uma mão na barriga 3. Inspire pelo nariz contando até 4 4. Sinta a barriga subir (não o peito) 5. Expire pela boca contando até 6 6. Repita 10 vezes','Faça antes de dormir',NULL,NULL,2);
/*!40000 ALTER TABLE `technique` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technique_history`
--

DROP TABLE IF EXISTS `technique_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `technique_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `technique_id` int NOT NULL,
  `date` datetime DEFAULT NULL,
  `initial_pain_scale` int DEFAULT NULL,
  `final_pain_scale` int DEFAULT NULL,
  `sensation_description` text,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `technique_id` (`technique_id`),
  CONSTRAINT `technique_history_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `technique_history_ibfk_2` FOREIGN KEY (`technique_id`) REFERENCES `technique` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `technique_history`
--

LOCK TABLES `technique_history` WRITE;
/*!40000 ALTER TABLE `technique_history` DISABLE KEYS */;
INSERT INTO `technique_history` VALUES (1,1,1,'2025-10-25 10:15:00',3,2,'Não senti dor alguma.'),(2,1,4,'2025-10-26 16:00:00',4,2,'Senti muita dor.'),(3,2,2,'2025-10-25 09:15:00',5,3,'Não senti dor.'),(4,1,1,'2025-10-25 10:15:00',3,2,'Não senti dor alguma.'),(5,1,1,'2025-10-27 08:30:00',4,2,'Me sinto mais relaxado.'),(6,1,1,'2025-10-29 19:00:00',5,3,'Ajudou a acalmar.'),(7,1,1,'2025-11-01 07:45:00',3,1,'Excelente para começar o dia.'),(8,1,1,'2025-11-05 21:00:00',4,2,'Dormi melhor depois.'),(9,1,4,'2025-10-26 16:00:00',4,2,'Senti muita dor.'),(10,1,4,'2025-10-30 20:00:00',5,3,'Aliviou a tensão nos ombros.'),(11,1,4,'2025-11-03 18:30:00',4,2,'Muito relaxante.'),(12,1,2,'2025-10-28 22:00:00',6,4,'Ajudou a dormir.'),(13,1,2,'2025-11-02 21:30:00',5,3,'Senti tontura leve no início.'),(14,1,3,'2025-11-04 14:00:00',3,2,'Alívio rápido.'),(15,1,5,'2025-11-06 16:00:00',5,3,'O calor das mãos ajudou muito.'),(16,2,2,'2025-10-25 09:15:00',5,3,'Não senti dor.'),(17,2,2,'2025-10-28 20:00:00',6,4,'Melhorou meu sono.'),(18,2,2,'2025-11-01 21:00:00',5,3,'Muito calmante.'),(19,2,2,'2025-11-05 22:00:00',4,2,'Excelente técnica.'),(20,2,1,'2025-10-26 10:00:00',4,3,'Senti mais tranquila.'),(21,2,1,'2025-10-31 08:00:00',5,3,'Boa para começar o dia.'),(22,2,1,'2025-11-04 07:30:00',3,2,'Me sinto revigorada.'),(23,2,4,'2025-10-27 19:00:00',6,4,'Relaxei completamente.'),(24,2,4,'2025-11-02 18:00:00',5,3,'Aliviou dor nas mãos.'),(25,2,5,'2025-10-29 15:00:00',4,2,'Calor reconfortante.'),(26,2,5,'2025-11-03 16:30:00',5,3,'Usei óleo morno, ficou ótimo.'),(27,2,5,'2025-11-06 14:00:00',4,2,'Sempre me ajuda.'),(28,2,3,'2025-11-01 12:00:00',3,1,'Alívio instantâneo.'),(29,2,3,'2025-11-05 13:00:00',4,2,'Rápido e eficaz.'),(30,1,1,'2025-10-25 10:15:00',3,2,'Não senti dor alguma.'),(31,1,4,'2025-10-26 16:00:00',4,2,'Senti muita dor.'),(32,2,2,'2025-10-25 09:15:00',5,3,'Não senti dor.'),(33,1,1,'2025-10-25 10:15:00',3,2,'Não senti dor alguma.'),(34,1,1,'2025-10-27 08:30:00',4,2,'Me sinto mais relaxado.'),(35,1,1,'2025-10-29 19:00:00',5,3,'Ajudou a acalmar.'),(36,1,1,'2025-11-01 07:45:00',3,1,'Excelente para começar o dia.'),(37,1,1,'2025-11-05 21:00:00',4,2,'Dormi melhor depois.'),(38,1,4,'2025-10-26 16:00:00',4,2,'Senti muita dor.'),(39,1,4,'2025-10-30 20:00:00',5,3,'Aliviou a tensão nos ombros.'),(40,1,4,'2025-11-03 18:30:00',4,2,'Muito relaxante.'),(41,1,2,'2025-10-28 22:00:00',6,4,'Ajudou a dormir.'),(42,1,2,'2025-11-02 21:30:00',5,3,'Senti tontura leve no início.'),(43,1,3,'2025-11-04 14:00:00',3,2,'Alívio rápido.'),(44,1,5,'2025-11-06 16:00:00',5,3,'O calor das mãos ajudou muito.'),(45,2,2,'2025-10-25 09:15:00',5,3,'Não senti dor.'),(46,2,2,'2025-10-28 20:00:00',6,4,'Melhorou meu sono.'),(47,2,2,'2025-11-01 21:00:00',5,3,'Muito calmante.'),(48,2,2,'2025-11-05 22:00:00',4,2,'Excelente técnica.'),(49,2,1,'2025-10-26 10:00:00',4,3,'Senti mais tranquila.'),(50,2,1,'2025-10-31 08:00:00',5,3,'Boa para começar o dia.'),(51,2,1,'2025-11-04 07:30:00',3,2,'Me sinto revigorada.'),(52,2,4,'2025-10-27 19:00:00',6,4,'Relaxei completamente.'),(53,2,4,'2025-11-02 18:00:00',5,3,'Aliviou dor nas mãos.'),(54,2,5,'2025-10-29 15:00:00',4,2,'Calor reconfortante.'),(55,2,5,'2025-11-03 16:30:00',5,3,'Usei óleo morno, ficou ótimo.'),(56,2,5,'2025-11-06 14:00:00',4,2,'Sempre me ajuda.'),(57,2,3,'2025-11-01 12:00:00',3,1,'Alívio instantâneo.'),(58,2,3,'2025-11-05 13:00:00',4,2,'Rápido e eficaz.');
/*!40000 ALTER TABLE `technique_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technique_type`
--

DROP TABLE IF EXISTS `technique_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `technique_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `technique_type`
--

LOCK TABLES `technique_type` WRITE;
/*!40000 ALTER TABLE `technique_type` DISABLE KEYS */;
INSERT INTO `technique_type` VALUES (1,'Técnicas de alívio'),(2,'Alongamentos guiados'),(3,'Técnicas de respiração'),(4,'Alongamentos guiados'),(5,'Técnicas de respiração');
/*!40000 ALTER TABLE `technique_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_comorbidity`
--

DROP TABLE IF EXISTS `user_comorbidity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_comorbidity` (
  `user_id` int NOT NULL,
  `comorbidity_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`comorbidity_id`),
  KEY `comorbidity_id` (`comorbidity_id`),
  CONSTRAINT `user_comorbidity_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `user_comorbidity_ibfk_2` FOREIGN KEY (`comorbidity_id`) REFERENCES `comorbidity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_comorbidity`
--

LOCK TABLES `user_comorbidity` WRITE;
/*!40000 ALTER TABLE `user_comorbidity` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_comorbidity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `password_hash` varchar(255) DEFAULT NULL,
  `font_size` int DEFAULT NULL,
  `contrast` int DEFAULT NULL,
  `reading_mode` tinyint DEFAULT NULL,
  `allow_data_collection` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Marcos','Marcos101','marcos_olimpia@yahoo.com.br',27,'Masculino','51999999999','hash123',2,0,0,1),(2,'Maria','Maria10','maria_oliveira@yahoo.com.br',65,'Feminino','51988888888','hash456',2,1,1,1),(3,'Gajwhwh Isjwj',NULL,'jsjsjwj@kskwks',111,'M','51980325571','hsjsjs',2,1,0,0),(4,'Gabe',NULL,'g@gmail.com',21,'M','51999999999','q',2,1,1,1),(5,'G',NULL,'s',1,'F','98989898989','q',2,0,1,1),(6,'Gabriel',NULL,'gabrielkuhn@mx2.unisc.br',21,'M','51999999999','123456',4,1,1,1),(7,'Gabe','gabe','gabe@gmail.com',21,'M','51999999999','1111',2,0,1,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-23 20:27:50
