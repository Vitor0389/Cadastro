package com.cadastro

enum class Sexo{
    MASCULINO,
    FEMININO
}
enum class Estados{
    AC,
    AL,
    AP,
    AM,
    BA,
    CE,
    DF,
    ES,
    GO,
    MA,
    MT,
    MS,
    MG,
    PA,
    PB,
    PR,
    PE,
    PI,
    RJ,
    RN,
    RS,
    RO,
    RR,
    SC,
    SP,
    SE,
    TO;
}

data class Formulario(
    val nome : String,
    val telefone : String,
    val email : String,
    val sexo : Sexo,
    val cidade : String,
    val estado: Estados
)
