-- Eliminar la columna "deleted" de la tabla "users"
ALTER TABLE public.users
DROP COLUMN deleted;

-- Agregar las columnas solicitadas con valor predeterminado "true"
ALTER TABLE public.users
ADD COLUMN enabled BOOLEAN NOT NULL DEFAULT true,
ADD COLUMN account_non_expired BOOLEAN NOT NULL DEFAULT true,
ADD COLUMN account_non_locked BOOLEAN NOT NULL DEFAULT true,
ADD COLUMN credentials_non_expired BOOLEAN NOT NULL DEFAULT true,
ADD COLUMN role VARCHAR(100) NOT NULL,
ADD COLUMN password VARCHAR(255) NOT NULL;