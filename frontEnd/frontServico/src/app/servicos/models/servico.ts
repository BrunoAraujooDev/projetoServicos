import { Funcionario } from "src/app/funcionario/models/funcionario"

export interface Servico {
    idServico?: Number
    titulo: String
    descricao: String
    dataEntrada: Date
    status: String
    funcionario: Funcionario
    idOrcamento?: Number
    idCliente?: Number

}
