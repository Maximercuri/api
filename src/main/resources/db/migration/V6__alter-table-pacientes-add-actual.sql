ALTER TABLE pacientes ADD COLUMN "actual" boolean DEFAULT true NOT NULL;
UPDATE pacientes SET actual = true;