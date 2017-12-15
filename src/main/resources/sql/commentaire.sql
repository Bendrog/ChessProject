CREATE TABLE `ouverture` (
  `ouverture_id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  PRIMARY KEY (`ouverture_id`)
);
CREATE TABLE `commentaire` (
  `commentaire_id` INT NOT NULL AUTO_INCREMENT,
  `texte` LONGTEXT NOT NULL,
  `auteur` VARCHAR(100) NULL,
  `likes` INT(10) NULL DEFAULT 0,
  `nom` varchar(30) NOT NULL,
  PRIMARY KEY (`commentaire_id`),
  KEY ouverture_id_fk (nom),
  CONSTRAINT ouverture_id_fk FOREIGN KEY (ouverture_id) REFERENCES ouverture (ouverture_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);