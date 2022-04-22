import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FuncionarioHttpService } from '../../services/funcionario-http.service';
import { Funcionario } from '../../models/funcionario';

@Component({
  selector: 'app-funcionario',
  templateUrl: './funcionario.component.html',
  styleUrls: ['./funcionario.component.css']
})
export class FuncionarioComponent implements OnInit {

idFuncionario!: string | null
funcionario!: Funcionario

  constructor(
    private route: ActivatedRoute,
    private funHttpService: FuncionarioHttpService
  ) { }

  ngOnInit(): void {
    this.idFuncionario = this.route.snapshot.paramMap.get("idFuncionario") || "";

    this.funHttpService.getFuncionarioById(parseInt(this.idFuncionario)).subscribe(
      item => this.funcionario = item
    )


    // this.route.paramMap.subscribe(
    //   url => {
    //     this.idFuncionario = url.get("idFuncionario") || "";
    //     console.log(this.idFuncionario)
    //      this.funHttpService.getFuncionarioById(parseInt(this.idFuncionario))
    //      .subscribe( 
    //        item => {
    //         this.infoFuncionario = item
    //         console.log(this.infoFuncionario)
    //        }
    //      )
    //   } 
    // )
  }

}
