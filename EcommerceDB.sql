USE [master]
GO
/****** Object:  Database [EcommerceDB]    Script Date: 06/05/2024 09:57:50 p. m. ******/
CREATE DATABASE [EcommerceDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'EcommerceDB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\EcommerceDB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'EcommerceDB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\EcommerceDB_log.ldf' , SIZE = 73728KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [EcommerceDB] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [EcommerceDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [EcommerceDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [EcommerceDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [EcommerceDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [EcommerceDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [EcommerceDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [EcommerceDB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [EcommerceDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [EcommerceDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [EcommerceDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [EcommerceDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [EcommerceDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [EcommerceDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [EcommerceDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [EcommerceDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [EcommerceDB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [EcommerceDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [EcommerceDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [EcommerceDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [EcommerceDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [EcommerceDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [EcommerceDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [EcommerceDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [EcommerceDB] SET RECOVERY FULL 
GO
ALTER DATABASE [EcommerceDB] SET  MULTI_USER 
GO
ALTER DATABASE [EcommerceDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [EcommerceDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [EcommerceDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [EcommerceDB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [EcommerceDB] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [EcommerceDB] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'EcommerceDB', N'ON'
GO
ALTER DATABASE [EcommerceDB] SET QUERY_STORE = ON
GO
ALTER DATABASE [EcommerceDB] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [EcommerceDB]
GO
/****** Object:  Table [dbo].[Carrito]    Script Date: 06/05/2024 09:57:51 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Carrito](
	[ID_Usuario] [int] NOT NULL,
	[ID_Producto] [int] NOT NULL,
	[FechaAgregado] [datetime] NOT NULL,
	[Cantidad] [int] NOT NULL,
	[Total] [decimal](10, 2) NOT NULL,
 CONSTRAINT [PK_Carrito] PRIMARY KEY CLUSTERED 
(
	[ID_Usuario] ASC,
	[ID_Producto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CategoriaProducto]    Script Date: 06/05/2024 09:57:51 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CategoriaProducto](
	[ID_CategoriaProducto] [int] IDENTITY(1,1) NOT NULL,
	[Categoria] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_CategoriaProducto] PRIMARY KEY CLUSTERED 
(
	[ID_CategoriaProducto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Comentario]    Script Date: 06/05/2024 09:57:51 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Comentario](
	[ID_Comentario] [int] IDENTITY(1,1) NOT NULL,
	[ID_Usuario] [int] NOT NULL,
	[ID_Producto] [int] NOT NULL,
	[Comentario] [nvarchar](max) NOT NULL,
	[Fecha_Comentario] [datetime] NOT NULL,
 CONSTRAINT [PK_Comentario] PRIMARY KEY CLUSTERED 
(
	[ID_Comentario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Conversacion]    Script Date: 06/05/2024 09:57:51 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Conversacion](
	[ID_Conversacion] [int] IDENTITY(1,1) NOT NULL,
	[ID_Vendedor] [int] NOT NULL,
	[ID_Comprador] [int] NOT NULL,
	[Fecha_Ultimo_Mensaje] [datetime] NOT NULL,
 CONSTRAINT [PK_Conversacion] PRIMARY KEY CLUSTERED 
(
	[ID_Conversacion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DetalleCompra]    Script Date: 06/05/2024 09:57:51 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DetalleCompra](
	[ID_DetalleCompra] [int] IDENTITY(1,1) NOT NULL,
	[ID_Usuario] [int] NOT NULL,
	[ID_Producto] [int] NOT NULL,
	[Total] [decimal](18, 2) NOT NULL,
	[Fecha_Entregado] [datetime] NOT NULL,
	[Cantidad] [int] NOT NULL,
 CONSTRAINT [PK_DetalleCompra] PRIMARY KEY CLUSTERED 
(
	[ID_DetalleCompra] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[EstadoPedido]    Script Date: 06/05/2024 09:57:51 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EstadoPedido](
	[ID_EstadoPedido] [int] IDENTITY(1,1) NOT NULL,
	[EstadoPedido] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_EstadoPedido] PRIMARY KEY CLUSTERED 
(
	[ID_EstadoPedido] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[EstadoProducto]    Script Date: 06/05/2024 09:57:51 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EstadoProducto](
	[ID_EstadoProducto] [int] IDENTITY(1,1) NOT NULL,
	[EstadoProducto] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_EstadoProducto] PRIMARY KEY CLUSTERED 
(
	[ID_EstadoProducto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Mensaje]    Script Date: 06/05/2024 09:57:51 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Mensaje](
	[ID_Mensaje] [int] IDENTITY(1,1) NOT NULL,
	[ID_Usuario_Emisor] [int] NOT NULL,
	[Mensaje] [nvarchar](max) NOT NULL,
	[Fecha_Envio] [datetime] NOT NULL,
	[Leido] [bit] NOT NULL,
	[ID_Conversacion] [int] NOT NULL,
 CONSTRAINT [PK_Mensaje] PRIMARY KEY CLUSTERED 
(
	[ID_Mensaje] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MetodoEnvio]    Script Date: 06/05/2024 09:57:51 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MetodoEnvio](
	[ID_MetodoEnvio] [int] IDENTITY(1,1) NOT NULL,
	[MetodoEnvio] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_MetodoEnvio] PRIMARY KEY CLUSTERED 
(
	[ID_MetodoEnvio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Notificacion]    Script Date: 06/05/2024 09:57:51 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Notificacion](
	[ID_Notificacion] [int] IDENTITY(1,1) NOT NULL,
	[ID_Usuario] [int] NOT NULL,
	[ID_TipoNoti] [int] NOT NULL,
	[Mensaje] [nvarchar](max) NOT NULL,
	[Fecha] [datetime] NOT NULL,
	[Leido] [bit] NOT NULL,
 CONSTRAINT [PK_Notificacion] PRIMARY KEY CLUSTERED 
(
	[ID_Notificacion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pedido]    Script Date: 06/05/2024 09:57:51 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pedido](
	[ID_Pedido] [int] IDENTITY(1,1) NOT NULL,
	[ID_Usuario] [int] NOT NULL,
	[ID_EstadoPedido] [int] NOT NULL,
	[ID_MetodoEnvio] [int] NOT NULL,
	[Direccion] [nvarchar](max) NOT NULL,
	[ID_Producto] [int] NOT NULL,
	[Cantidad] [int] NOT NULL,
	[Total] [decimal](18, 2) NOT NULL,
	[FechaPedido] [datetime] NOT NULL,
 CONSTRAINT [PK_Pedido] PRIMARY KEY CLUSTERED 
(
	[ID_Pedido] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Producto]    Script Date: 06/05/2024 09:57:51 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Producto](
	[ID_Producto] [int] IDENTITY(1,1) NOT NULL,
	[ID_Usuario] [int] NOT NULL,
	[Nombre] [nvarchar](255) NOT NULL,
	[Descripcion] [nvarchar](max) NULL,
	[Precio] [decimal](18, 2) NOT NULL,
	[Cantidad_Disponible] [int] NOT NULL,
	[ID_CategoriaProducto] [int] NOT NULL,
	[Fecha_Creacion] [datetime] NOT NULL,
	[ID_EstadoProducto] [int] NOT NULL,
	[ImagenURL] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_Producto] PRIMARY KEY CLUSTERED 
(
	[ID_Producto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TipoNotificacion]    Script Date: 06/05/2024 09:57:51 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TipoNotificacion](
	[ID_TipoNoti] [int] IDENTITY(1,1) NOT NULL,
	[TipoNotificacion] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_TipoNotificacion] PRIMARY KEY CLUSTERED 
(
	[ID_TipoNoti] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Usuario]    Script Date: 06/05/2024 09:57:51 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Usuario](
	[ID_Usuario] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [nvarchar](max) NOT NULL,
	[Apellido] [nvarchar](max) NOT NULL,
	[Correo_Electronico] [nvarchar](max) NOT NULL,
	[Contraseña] [nvarchar](max) NOT NULL,
	[Direccion] [nvarchar](max) NULL,
	[Telefono] [nvarchar](max) NOT NULL,
	[Fecha_Registro] [date] NOT NULL,
	[Fecha_Nacimiento] [date] NOT NULL,
	[ImagenURL] [nvarchar](max) NOT NULL,
	[DineroFalso] [float] NULL,
 CONSTRAINT [PK_Usuario] PRIMARY KEY CLUSTERED 
(
	[ID_Usuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Usuario_Conversacion]    Script Date: 06/05/2024 09:57:51 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Usuario_Conversacion](
	[ID_Usuario_Conversacion] [int] IDENTITY(1,1) NOT NULL,
	[ID_Conversacion] [int] NOT NULL,
	[ID_Usuario] [int] NOT NULL,
 CONSTRAINT [PK_Usuario_Conversacion] PRIMARY KEY CLUSTERED 
(
	[ID_Usuario_Conversacion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Carrito]  WITH CHECK ADD  CONSTRAINT [FK_Carrito_Producto] FOREIGN KEY([ID_Producto])
REFERENCES [dbo].[Producto] ([ID_Producto])
GO
ALTER TABLE [dbo].[Carrito] CHECK CONSTRAINT [FK_Carrito_Producto]
GO
ALTER TABLE [dbo].[Carrito]  WITH CHECK ADD  CONSTRAINT [FK_Carrito_Usuario] FOREIGN KEY([ID_Usuario])
REFERENCES [dbo].[Usuario] ([ID_Usuario])
GO
ALTER TABLE [dbo].[Carrito] CHECK CONSTRAINT [FK_Carrito_Usuario]
GO
ALTER TABLE [dbo].[Comentario]  WITH CHECK ADD  CONSTRAINT [FK_Comentario_Producto] FOREIGN KEY([ID_Producto])
REFERENCES [dbo].[Producto] ([ID_Producto])
GO
ALTER TABLE [dbo].[Comentario] CHECK CONSTRAINT [FK_Comentario_Producto]
GO
ALTER TABLE [dbo].[Comentario]  WITH CHECK ADD  CONSTRAINT [FK_Comentario_Usuario] FOREIGN KEY([ID_Usuario])
REFERENCES [dbo].[Usuario] ([ID_Usuario])
GO
ALTER TABLE [dbo].[Comentario] CHECK CONSTRAINT [FK_Comentario_Usuario]
GO
ALTER TABLE [dbo].[DetalleCompra]  WITH CHECK ADD  CONSTRAINT [FK_DetalleCompra_Producto] FOREIGN KEY([ID_Producto])
REFERENCES [dbo].[Producto] ([ID_Producto])
GO
ALTER TABLE [dbo].[DetalleCompra] CHECK CONSTRAINT [FK_DetalleCompra_Producto]
GO
ALTER TABLE [dbo].[DetalleCompra]  WITH CHECK ADD  CONSTRAINT [FK_DetalleCompra_Usuario] FOREIGN KEY([ID_Usuario])
REFERENCES [dbo].[Usuario] ([ID_Usuario])
GO
ALTER TABLE [dbo].[DetalleCompra] CHECK CONSTRAINT [FK_DetalleCompra_Usuario]
GO
ALTER TABLE [dbo].[Mensaje]  WITH CHECK ADD  CONSTRAINT [FK_Mensaje_Conversacion] FOREIGN KEY([ID_Conversacion])
REFERENCES [dbo].[Conversacion] ([ID_Conversacion])
GO
ALTER TABLE [dbo].[Mensaje] CHECK CONSTRAINT [FK_Mensaje_Conversacion]
GO
ALTER TABLE [dbo].[Mensaje]  WITH CHECK ADD  CONSTRAINT [FK_Mensaje_Usuario] FOREIGN KEY([ID_Usuario_Emisor])
REFERENCES [dbo].[Usuario] ([ID_Usuario])
GO
ALTER TABLE [dbo].[Mensaje] CHECK CONSTRAINT [FK_Mensaje_Usuario]
GO
ALTER TABLE [dbo].[Notificacion]  WITH CHECK ADD  CONSTRAINT [FK_Notificacion_TipoNotificacion] FOREIGN KEY([ID_TipoNoti])
REFERENCES [dbo].[TipoNotificacion] ([ID_TipoNoti])
GO
ALTER TABLE [dbo].[Notificacion] CHECK CONSTRAINT [FK_Notificacion_TipoNotificacion]
GO
ALTER TABLE [dbo].[Notificacion]  WITH CHECK ADD  CONSTRAINT [FK_Notificacion_Usuario] FOREIGN KEY([ID_Usuario])
REFERENCES [dbo].[Usuario] ([ID_Usuario])
GO
ALTER TABLE [dbo].[Notificacion] CHECK CONSTRAINT [FK_Notificacion_Usuario]
GO
ALTER TABLE [dbo].[Pedido]  WITH CHECK ADD  CONSTRAINT [FK_Pedido_EstadoPedido] FOREIGN KEY([ID_EstadoPedido])
REFERENCES [dbo].[EstadoPedido] ([ID_EstadoPedido])
GO
ALTER TABLE [dbo].[Pedido] CHECK CONSTRAINT [FK_Pedido_EstadoPedido]
GO
ALTER TABLE [dbo].[Pedido]  WITH CHECK ADD  CONSTRAINT [FK_Pedido_MetodoEnvio] FOREIGN KEY([ID_MetodoEnvio])
REFERENCES [dbo].[MetodoEnvio] ([ID_MetodoEnvio])
GO
ALTER TABLE [dbo].[Pedido] CHECK CONSTRAINT [FK_Pedido_MetodoEnvio]
GO
ALTER TABLE [dbo].[Pedido]  WITH CHECK ADD  CONSTRAINT [FK_Pedido_Producto] FOREIGN KEY([ID_Producto])
REFERENCES [dbo].[Producto] ([ID_Producto])
GO
ALTER TABLE [dbo].[Pedido] CHECK CONSTRAINT [FK_Pedido_Producto]
GO
ALTER TABLE [dbo].[Pedido]  WITH CHECK ADD  CONSTRAINT [FK_Pedido_Usuario] FOREIGN KEY([ID_Usuario])
REFERENCES [dbo].[Usuario] ([ID_Usuario])
GO
ALTER TABLE [dbo].[Pedido] CHECK CONSTRAINT [FK_Pedido_Usuario]
GO
ALTER TABLE [dbo].[Producto]  WITH CHECK ADD  CONSTRAINT [FK_Producto_CategoriaProducto] FOREIGN KEY([ID_CategoriaProducto])
REFERENCES [dbo].[CategoriaProducto] ([ID_CategoriaProducto])
GO
ALTER TABLE [dbo].[Producto] CHECK CONSTRAINT [FK_Producto_CategoriaProducto]
GO
ALTER TABLE [dbo].[Producto]  WITH CHECK ADD  CONSTRAINT [FK_Producto_EstadoProducto] FOREIGN KEY([ID_EstadoProducto])
REFERENCES [dbo].[EstadoProducto] ([ID_EstadoProducto])
GO
ALTER TABLE [dbo].[Producto] CHECK CONSTRAINT [FK_Producto_EstadoProducto]
GO
ALTER TABLE [dbo].[Producto]  WITH CHECK ADD  CONSTRAINT [FK_Producto_Usuario] FOREIGN KEY([ID_Usuario])
REFERENCES [dbo].[Usuario] ([ID_Usuario])
GO
ALTER TABLE [dbo].[Producto] CHECK CONSTRAINT [FK_Producto_Usuario]
GO
ALTER TABLE [dbo].[Usuario_Conversacion]  WITH CHECK ADD  CONSTRAINT [FK_Usuario_Conversacion_Conversacion] FOREIGN KEY([ID_Conversacion])
REFERENCES [dbo].[Conversacion] ([ID_Conversacion])
GO
ALTER TABLE [dbo].[Usuario_Conversacion] CHECK CONSTRAINT [FK_Usuario_Conversacion_Conversacion]
GO
ALTER TABLE [dbo].[Usuario_Conversacion]  WITH CHECK ADD  CONSTRAINT [FK_Usuario_Conversacion_Usuario] FOREIGN KEY([ID_Usuario])
REFERENCES [dbo].[Usuario] ([ID_Usuario])
GO
ALTER TABLE [dbo].[Usuario_Conversacion] CHECK CONSTRAINT [FK_Usuario_Conversacion_Usuario]
GO
USE [master]
GO
ALTER DATABASE [EcommerceDB] SET  READ_WRITE 
GO
