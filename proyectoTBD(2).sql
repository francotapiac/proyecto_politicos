-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 23-07-2020 a las 16:29:27
-- Versión del servidor: 5.7.30-0ubuntu0.18.04.1
-- Versión de PHP: 7.2.24-0ubuntu0.18.04.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyectoTBD`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `national_actuality`
--

CREATE TABLE `national_actuality` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `national_actuality`
--

INSERT INTO `national_actuality` (`id`, `name`, `description`) VALUES
(1, 'covid', 'Abreviación del virus coronavirus'),
(2, 'coronavirus', 'Virus que causo la pandemia mundial'),
(3, 'estallido social', 'Manifestaciones y protestas causadas por el descontento social y la deisgualdad en chile'),
(4, 'desigualdad', 'desigualdad en chile'),
(5, 'afp', 'asociacion de fondos de pensiones de chile'),
(6, 'sueldo', 'sueldos de personas que residen en chile'),
(7, 'represion', 'represion, mutilacion y abuso por parte de las fuerzas de orden publico (carabineros)'),
(8, 'militares', 'Fuerzas armadas de chile'),
(9, 'araucania', 'represion en la region de la araucania'),
(10, 'constitucion', 'debate sobre nueva constitucion ()Apruebo/Rechazo'),
(11, 'salud', 'Sistema de salud de chile');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `political_party`
--

CREATE TABLE `political_party` (
  `id` int(11) NOT NULL,
  `name` varchar(300) NOT NULL,
  `aprobation` double NOT NULL,
  `description` text NOT NULL,
  `aka_name` varchar(255) NOT NULL,
  `aprobation_actuality` int(11) NOT NULL,
  `count_negative` int(11) DEFAULT NULL,
  `count_positive` int(11) DEFAULT NULL,
  `count_very_negative` int(11) DEFAULT NULL,
  `count_very_positive` int(11) DEFAULT NULL,
   `url_image` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `political_party`
--

INSERT INTO `political_party` (`id`, `name`, `aprobation`, `description`, `aka_name`, `aprobation_actuality`, `count_negative`, `count_positive`, `count_very_negative`, `count_very_positive`,`url_image`) VALUES
(1, 'Union Democrata Independiente', 0, 'cambiar esta descripcion', '', 0, 0, 0, 0, 0,'https://www.udi.cl/wp-content/uploads/2018/03/logoUDI-01.svg'),
(2, 'Renovacion Nacional', 0, 'cambiar esta descripcion', '', 0, 0, 0, 0, 0,'http://www.rn.cl/wp-content/uploads/2020/06/logo_rn.png'),
(3, 'Comunista', 0, 'cambiar esta descripcion', '', 0, 0, 0, 0, 0,'http://pcchile.cl/wp-content/uploads/2018/06/logo90x90-3-3.png'),
(4, 'Socialista', 0, 'cambiar esta descripcion', '', 0, 0, 0, 0, 0,'https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/Emblem_of_the_Socialist_Party_of_Chile.svg/160px-Emblem_of_the_Socialist_Party_of_Chile.svg.png'),
(5, 'Democracia Cristiana', 0, 'cambiar esta descripcion', '', 0, 0, 0, 0, 0,'https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Emblem_of_the_Christian_Democrat_Party_of_Chile.svg/98px-Emblem_of_the_Christian_Democrat_Party_of_Chile.svg.png'),
(6, 'Independientes', 0, 'cambiar esta descripcion', '', 0, 0, 0, 0, 0,'http://latitudinformativa.com/wp-content/uploads/2018/04/LogoIndependiente.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `politician`
--

CREATE TABLE `politician` (
  `id` int(11) NOT NULL,
  `id_political_party` int(11) NOT NULL,
  `real_name` varchar(100) DEFAULT NULL,
  `aka_name` varchar(100) DEFAULT NULL,
  `aprobation` double NOT NULL,
  `count_negative` int(11) DEFAULT NULL,
  `count_positive` int(11) DEFAULT NULL,
  `count_very_negative` int(11) DEFAULT NULL,
  `count_very_positive` int(11) DEFAULT NULL,
  `aprobation_actuality` double NOT NULL,
  `description` text NOT NULL,
  `url_image` text NOT NULL
  
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `politician`
--

INSERT INTO `politician` (`id`, `id_political_party`, `real_name`, `aka_name`, `aprobation`, `count_negative`, `count_positive`, `count_very_negative`, `count_very_positive`, `aprobation_actuality`, `description`,`url_image`) VALUES
(1, 1, 'Jacqueline van Rysselberghe', 'van rysselberghe', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','https://www.ilustrado.cl/wp-content/uploads/2020/06/Jacqueline-Van-Rysselberghe.jpg'),
(2, 1, 'Iván Moreira', 'moreira', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','https://www.duna.cl/media/2020/02/Iv%C3%A1n-Moreira-cuadernos-fomentan-violencia.jpg'),
(3, 1, 'Rodolfo Carter', 'carter', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','https://www.latercera.com/resizer/VJPN6EHSpDy4x6OB_ADyXC0nJNE=/900x600/smart/cloudfront-us-east-1.images.arcpublishing.com/copesa/FTZ7BYXY4JAWDBR2QIDD3OFJUE.jpg'),
(4, 1, 'Joaquín Lavín', 'lavin', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','https://www.elperiodista.cl/wp-content/uploads/2019/07/Joaquin_Lavin-e1563460282910-750x430.jpg'),
(5, 3, 'Karol Cariola', 'karol cariola', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','https://www.bcn.cl/obtieneimagen?id=documentos/10221.1/66347/5/BCNChile_Diputados_20170518_F005-G.jpg'),
(6, 3, 'Daniel Jadue', 'jadue', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','https://www.radiousach.cl/rusach/site/artic/20200703/imag/foto_0000000220200703124029/daniel_jadue.jpg'),
(7, 3, 'Guillermo Teillier', 'guillermo teillier', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','https://assets.radioagricultura.cl//2019/01/A_UNO_1005429_e0137.jpg'),
(8, 2, 'Camila Flores', 'camila flores', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','https://www.lacuarta.com/wp-content/uploads/2018/10/Camila-Flores.jpg'),
(9, 2, 'German Codina', 'codina', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','https://radio.uchile.cl/wp-content/uploads/2020/01/Germ%C3%A1n-Codina.jpg'),
(10, 2, 'Manuel José Ossandón', 'ossandon', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','https://www.elperiodista.cl/wp-content/uploads/2019/11/Imagen-Manuel-Jose-Ossandon-02-450x300.jpg'),
(11, 2, 'Sebastián Piñera', 'piñera', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','https://media.elmostrador.cl/2019/11/pinera.jpg'),
(12, 2, 'Diego Schalper', 'schalper', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','http://www.bcn.cl/obtieneimagen?id=documentos/10221.1/70055/BCNChile_Congresistas_20180404_F005-G.jpg'),
(13, 4, 'Carlos Montes', 'carlos montes', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','https://www.bcn.cl/obtieneimagen?id=documentos/10221.1/15377/1/BCN%20-%20Carlos%20Montes%20-%200003.jpg'),
(14, 4, 'Juan Pablo Letelier', 'letelier', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','https://www.duna.cl/media/2018/09/letelier_16836.jpg'),
(15, 4, 'Álvaro Elizalde', 'elizalde', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','http://www.gamba.cl/wp-content/uploads/2019/06/alvaro-elizalde-narco-720x405.jpg'),
(16, 4, 'Michelle Bachelet', 'bachelet', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','https://mujeresbacanas.com/wp-content/uploads/2018/02/Portrait_Michelle_Bachelet-1290x715.jpg'),
(17, 5, 'Carolina Goic', 'carolina goic', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','https://www.bcn.cl/obtieneimagen?id=documentos/10221.1/15239/1/IMG_1078.JPG'),
(18, 5, 'Ximena Rincón', 'ximena rincon', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','https://upload.wikimedia.org/wikipedia/commons/thumb/8/8f/Senadora_Ximena_Rinc%C3%B3n.jpg/250px-Senadora_Ximena_Rinc%C3%B3n.jpg'),
(19, 5, 'Yasna Provoste', 'yasna provoste', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','https://media.cnnchile.com/sites/2/2020/01/A_UNO_1103222_6c5f7-740x430.jpg'),
(20, 6, 'Gabriel Boric', 'boric', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','https://www.latercera.com/resizer/JIMbgtDciwQ6MzbBr8yLKNsVJd8=/900x600/smart/arc-anglerfish-arc2-prod-copesa.s3.amazonaws.com/public/YKFF4XDKJNE33J6WCM5DCENVCE.jpg'),
(21, 6, 'Giorgio Jackson', 'giorgio jackson', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','https://www.latercera.com/resizer/ZgKqbfV_ErhYanX6pJwS5_uYAKg=/900x600/smart/arc-anglerfish-arc2-prod-copesa.s3.amazonaws.com/public/PJ54HSEI65H4FPGFVFQHMNISAI.jpg'),
(22, 6, 'José Antonio Kast', 'jose antonio kast', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','https://www.theclinic.cl/wp-content/uploads/2017/09/A_UNO_840341-e1505869438876.jpg'),
(23, 6, 'Beatriz Sánchez', 'beatriz sanchez', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','https://media.elmostrador.cl/2019/05/Beatriz-Sanchez-700x393.jpg'),
(24, 6, 'Pamela Jiles', 'pamela jiles', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','https://media.elmostrador.cl/2017/12/Pamela-Jiles-2-700x467.jpg'),
(25, 6, 'Felipe Kast', 'felipe kast', 0, NULL, NULL, NULL, NULL, 0, 'cambiar esta descripcion','https://www.duna.cl/media/2018/01/felipe-kast-aton1.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `politician_national_actuality`
--

CREATE TABLE `politician_national_actuality` (
  `id` int(11) NOT NULL,
  `id_politician` int(11) NOT NULL,
  `id_actuality` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `national_actuality`
--
ALTER TABLE `national_actuality`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `political_party`
--
ALTER TABLE `political_party`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `politician`
--
ALTER TABLE `politician`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_political_party` (`id_political_party`);

--
-- Indices de la tabla `politician_national_actuality`
--
ALTER TABLE `politician_national_actuality`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_politician` (`id_politician`),
  ADD KEY `fk_id_actuality` (`id_actuality`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `politician`
--
ALTER TABLE `politician`
  ADD CONSTRAINT `fk_id_political_party` FOREIGN KEY (`id_political_party`) REFERENCES `political_party` (`id`);

--
-- Filtros para la tabla `politician_national_actuality`
--
ALTER TABLE `politician_national_actuality`
  ADD CONSTRAINT `fk_id_actuality` FOREIGN KEY (`id_actuality`) REFERENCES `national_actuality` (`id`),
  ADD CONSTRAINT `fk_id_politician` FOREIGN KEY (`id_politician`) REFERENCES `politician` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
