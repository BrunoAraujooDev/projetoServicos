import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Funcionario } from '../../models/funcionario';
import { FuncionarioHttpService } from '../../services/funcionario-http.service';

@Component({
  selector: 'app-editar-funcionario',
  templateUrl: './editar-funcionario.component.html',
  styleUrls: ['./editar-funcionario.component.css']
})
export class EditarFuncionarioComponent implements OnInit {

  idFuncionario!: string | null
  funcionario!: Funcionario

  @ViewChild('fileInput')
  fileInput!: ElementRef

  constructor(
    private route: ActivatedRoute,
    private funHttpService: FuncionarioHttpService
  ) { }

  ngOnInit(): void {

    this.idFuncionario = this.route.snapshot.paramMap.get("idFuncionario") || "";

    this.funHttpService.getFuncionarioById((parseInt(this.idFuncionario))).subscribe(
      funcionario => this.funcionario = funcionario
    )
  }

  selectImage(): void{
    this.fileInput.nativeElement.click()
  }

}
