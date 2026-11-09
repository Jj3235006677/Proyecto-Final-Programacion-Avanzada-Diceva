import { Component , computed, inject, signal} from '@angular/core';

import { toSignal } from '@angular/core/rxjs-interop';
import { FormBuilder ,ReactiveFormsModule, Validators } from  '@angular/forms'

@Component({
  imports: [ReactiveFormsModule],
  selector: 'app-login',
  styleUrl: './login.css',
  templateUrl: './login.html',
})


export class Login {
  LoginForm= inject(FormBuilder).group({
    correo:['',[Validators.required,Validators.email]],
    contraseña:['',[Validators.required,Validators.minLength(6)]]



  });
  isLoading=signal(false);
  result=signal('');

  formStatus = toSignal(this.LoginForm.statusChanges,{initialValue:'INVALID' as const})
  canSubmit = computed(()=>this.formStatus()==='VALID'&& !this.isLoading());


  onSubmit():void{
    console.log("Credenciales", this.LoginForm.value);

    this.result.set('Intendo  ingresar ... ');
  }







}