USE  `tpfinal_2024` ;
SELECT f.titulo, r.fechaRefutada, r.fuenteEvidencia, r.provOrgOficial FROM refutaciones r join fakenews f ON r.idFakenews = f.idFakenews;
