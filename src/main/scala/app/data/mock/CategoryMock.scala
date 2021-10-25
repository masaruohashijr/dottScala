package app.data.mock

object CategoryMock{
    var cat = Array(
        "Saúde e Beleza",
        "Casa e Jardim",
        "Moda e Calçado",
        "Eletrodomésticos",
        "Supermercado e Bebidas",
        "Animais",
        "Telemóveis e Comunicações",
        "Desporto",
        "Relógios e Jóias",
        "Bebé, Criança e Brinquedos",
        "Informática e Eletrónica",
        "Imagem, Som e Home tech",
        "Livros",
        "Escritório e Papelaria",
        "Música, Filmes e Gaming",
        "Acessórios para Veículos",
        "Vouchers Experiência",
        "Produtos Artesanais"
    )


    def mock():String={
        val r = new scala.util.Random
        var pos = r.nextInt(18)
        cat(pos)
    }

}
