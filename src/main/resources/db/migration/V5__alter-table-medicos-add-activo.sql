ALTER TABLE medicos ADD COLUMN "activo" boolean DEFAULT true NOT NULL;
UPDATE medicos SET activo = true;