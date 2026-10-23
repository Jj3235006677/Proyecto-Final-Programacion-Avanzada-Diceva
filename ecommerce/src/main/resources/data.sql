-- =====================================================
-- 1. INSERTAR JUEGOS DE MESA
-- =====================================================

INSERT INTO JUEGOS_MESA
(ID, NOMBRE, DESCRIPCION, PRECIO_MONTO, STOCK,
 MINIMO_JUGADORES, MAXIMO_JUGADORES, MECANICA_DE_JUEGO,
 TEMATICA_DE_JUEGO, EDAD_RECOMENDADA, COMPLEJIDAD,
 ESTADO, CEDULA_PROVEEDOR, ELIMINACION_LOGICA, IMAGEN_URL)
VALUES
    ('aaaaaaaa-1111-1111-1111-111111111111',
     'Catan', 'Estrategia y gestión de recursos', 150000, 15,
     3, 4, 'Gestión de recursos', 'Civilización y exploración',
     10, 'INTERMEDIA', 'DISPONIBLE',
     'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', FALSE,
     'https://example.com/catan.jpg'),

    ('aaaaaaaa-2222-2222-2222-222222222222',
     'Dixit', 'Imaginación e interpretación de imágenes', 125000, 12,
     3, 6, 'Asociación de ideas', 'Fantasía y creatividad',
     8, 'BASICA', 'DISPONIBLE',
     'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', FALSE,
     'https://example.com/dixit.jpg'),

    ('aaaaaaaa-3333-3333-3333-333333333333',
     'Carcassonne', 'Construcción de territorios', 110000, 10,
     2, 5, 'Colocación de losetas', 'Edad Media',
     7, 'INTERMEDIA', 'DISPONIBLE',
     'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', FALSE,
     'https://example.com/carcassonne.jpg'),

    ('bbbbbbbb-1111-1111-1111-111111111111',
     'Ticket to Ride', 'Construcción de rutas ferroviarias', 180000, 8,
     2, 5, 'Construcción de rutas', 'Ferrocarriles y viajes',
     8, 'BASICA', 'DISPONIBLE',
     'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', FALSE,
     'https://example.com/ticket.jpg'),

    ('bbbbbbbb-2222-2222-2222-222222222222',
     'Pandemic', 'Juego cooperativo de crisis globales', 165000, 6,
     2, 4, 'Cooperación y gestión de acciones',
     'Salud y crisis global', 8, 'AVANZADA', 'DISPONIBLE',
     'cccccccc-cccc-cccc-cccc-cccccccccccc', FALSE,
     'https://example.com/pandemic.jpg'),

    ('cccccccc-1111-1111-1111-111111111111',
     'Azul', 'Construcción de patrones y mosaicos', 140000, 9,
     2, 4, 'Construcción de patrones', 'Arte y mosaicos',
     8, 'INTERMEDIA', 'DISPONIBLE',
     'cccccccc-cccc-cccc-cccc-cccccccccccc', FALSE,
     'https://example.com/azul.jpg'),

    ('cccccccc-2222-2222-2222-222222222222',
     'Chess', 'Juego clásico de estrategia', 65000, 20,
     2, 2, 'Estrategia abstracta', 'Estrategia y táctica',
     6, 'AVANZADA', 'DISPONIBLE',
     'dddddddd-dddd-dddd-dddd-dddddddddddd', FALSE,
     'https://example.com/chess.jpg'),

    ('dddddddd-1111-1111-1111-111111111111',
     'Monopoly', 'Negociación y gestión económica', 95000, 0,
     2, 6, 'Gestión económica', 'Bienes raíces y negocios',
     8, 'BASICA', 'AGOTADO',
     'dddddddd-dddd-dddd-dddd-dddddddddddd', FALSE,
     'https://example.com/monopoly.jpg');


-- =====================================================
-- 2. INSERTAR COMPRAS
-- =====================================================

INSERT INTO COMPRAS
(ID, CEDULA_USUARIO, FECHA_COMPRA, PRECIO_MONTO, ESTADO_PAGO)
VALUES
    ('eeeeeeee-1111-1111-1111-111111111111',
     '11111111-1111-1111-1111-111111111111',
     CURRENT_TIMESTAMP, 275000, 'APROBADO'),

    ('eeeeeeee-2222-2222-2222-222222222222',
     '22222222-2222-2222-2222-222222222222',
     CURRENT_TIMESTAMP, 290000, 'PENDIENTE'),

    ('eeeeeeee-3333-3333-3333-333333333333',
     '33333333-3333-3333-3333-333333333333',
     CURRENT_TIMESTAMP, 305000, 'APROBADO'),

    ('eeeeeeee-4444-4444-4444-444444444444',
     '44444444-4444-4444-4444-444444444444',
     CURRENT_TIMESTAMP, 215000, 'APROBADO'),

    ('eeeeeeee-5555-5555-5555-555555555555',
     '55555555-5555-5555-5555-555555555555',
     CURRENT_TIMESTAMP, 220000, 'PENDIENTE'),

    ('eeeeeeee-6666-6666-6666-666666666666',
     '66666666-6666-6666-6666-666666666666',
     CURRENT_TIMESTAMP, 250000, 'APROBADO'),

    ('eeeeeeee-7777-7777-7777-777777777777',
     '77777777-7777-7777-7777-777777777777',
     CURRENT_TIMESTAMP, 345000, 'APROBADO');


-- =====================================================
-- 3. ASOCIAR JUEGOS CON SUS COMPRAS
-- =====================================================

INSERT INTO COMPRA_JUEGOS (COMPRA_ID, JUEGO_ID)
VALUES
-- Compra 1: Catan + Dixit
('eeeeeeee-1111-1111-1111-111111111111',
 'aaaaaaaa-1111-1111-1111-111111111111'),
('eeeeeeee-1111-1111-1111-111111111111',
 'aaaaaaaa-2222-2222-2222-222222222222'),

-- Compra 2: Carcassonne + Ticket to Ride
('eeeeeeee-2222-2222-2222-222222222222',
 'aaaaaaaa-3333-3333-3333-333333333333'),
('eeeeeeee-2222-2222-2222-222222222222',
 'bbbbbbbb-1111-1111-1111-111111111111'),

-- Compra 3: Pandemic + Azul
('eeeeeeee-3333-3333-3333-333333333333',
 'bbbbbbbb-2222-2222-2222-222222222222'),
('eeeeeeee-3333-3333-3333-333333333333',
 'cccccccc-1111-1111-1111-111111111111'),

-- Compra 4: Chess + Catan
('eeeeeeee-4444-4444-4444-444444444444',
 'cccccccc-2222-2222-2222-222222222222'),
('eeeeeeee-4444-4444-4444-444444444444',
 'aaaaaaaa-1111-1111-1111-111111111111'),

-- Compra 5: Dixit + Monopoly
('eeeeeeee-5555-5555-5555-555555555555',
 'aaaaaaaa-2222-2222-2222-222222222222'),
('eeeeeeee-5555-5555-5555-555555555555',
 'dddddddd-1111-1111-1111-111111111111'),

-- Compra 6: Azul + Carcassonne
('eeeeeeee-6666-6666-6666-666666666666',
 'cccccccc-1111-1111-1111-111111111111'),
('eeeeeeee-6666-6666-6666-666666666666',
 'aaaaaaaa-3333-3333-3333-333333333333'),

-- Compra 7: Ticket to Ride + Pandemic
('eeeeeeee-7777-7777-7777-777777777777',
 'bbbbbbbb-1111-1111-1111-111111111111'),
('eeeeeeee-7777-7777-7777-777777777777',
 'bbbbbbbb-2222-2222-2222-222222222222');


-- =====================================================
-- 4. CONSULTAR COMPRAS CON SUS JUEGOS
-- =====================================================

SELECT
    C.ID AS COMPRA_ID,
    C.CEDULA_USUARIO,
    C.PRECIO_MONTO AS TOTAL_COMPRA,
    C.ESTADO_PAGO,
    J.ID AS JUEGO_ID,
    J.NOMBRE AS NOMBRE_JUEGO,
    J.PRECIO_MONTO AS PRECIO_JUEGO
FROM COMPRAS C
         INNER JOIN COMPRA_JUEGOS CJ
                    ON C.ID = CJ.COMPRA_ID
         INNER JOIN JUEGOS_MESA J
                    ON CJ.JUEGO_ID = J.ID
ORDER BY C.ID, J.NOMBRE;