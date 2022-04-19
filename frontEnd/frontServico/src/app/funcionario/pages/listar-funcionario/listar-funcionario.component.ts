import { Component, OnInit} from '@angular/core';
import { Funcionario } from '../../models/funcionario';
import { FuncionarioHttpService } from '../../services/funcionario-http.service';

@Component({
  selector: 'app-listar-funcionario',
  templateUrl: './listar-funcionario.component.html',
  styleUrls: ['./listar-funcionario.component.css']
})
export class ListarFuncionarioComponent implements OnInit {

  funcionarios: Funcionario[] = []

  columns: string[] = ['idFuncionario', 'nome', 'email', 'actions']

  constructor(
    private funHttpService: FuncionarioHttpService
  ) { }

  ngOnInit(): void {
    this.funHttpService.getFuncionarios().subscribe(
      (funcionarios) => this.funcionarios = funcionarios
    )
  }

}
