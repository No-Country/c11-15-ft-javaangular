import { PetallService } from 'src/app/services/petall.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CustomValidators } from 'ng2-validation';
import {
  Storage,
  ref,
  uploadBytes,
  listAll,
  getDownloadURL,
} from '@angular/fire/storage';
import { CreateMascota, Mascota } from 'src/app/models/pet.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css'],
})
export class FormComponent implements OnInit {
  images: string[];
  imgRef: string = '';

  constructor(
    private storage: Storage,
    private petallService: PetallService,
    private router: Router
  ) {
    this.images = [];
  }

  imgreferent = ref(this.storage);

  ngOnInit(): void {
    this.getImages();
  }

  nombre = new FormControl('', Validators.required);
  especie = new FormControl('', Validators.required);
  sex = new FormControl('', Validators.required);
  vacunado = new FormControl(false);
  desparacitado = new FormControl(false);
  esterilizado = new FormControl(false);
  fechaDeNacimiento = new FormControl('');
  nivelActividad = new FormControl('');
  size = new FormControl('', Validators.required);
  descripcion = new FormControl('', Validators.required);
  cuidados = new FormControl('', Validators.required);
  pais = new FormControl('', Validators.required);
  estado = new FormControl('', Validators.required);
  depar = new FormControl('', Validators.required);
  localidad = new FormControl('');
  contacto = new FormControl(0, (Validators.required, CustomValidators.min(8)));
  fotos = new FormControl(['']);
  termino = new FormControl(false, Validators.requiredTrue);
  datos = new FormControl(false, Validators.requiredTrue);
  novedades = new FormControl(false, Validators.requiredTrue);

  form = new FormGroup({
    nombre: this.nombre,
    especie: this.especie,
    sex: this.sex,
    vacunado: this.vacunado,
    desparacitado: this.desparacitado,
    esterilizado: this.esterilizado,
    fechaDeNacimiento: this.fechaDeNacimiento,
    nivelActividad: this.nivelActividad,
    size: this.size,
    descripcion: this.descripcion,
    cuidados: this.cuidados,
    pais: this.pais,
    estado: this.estado,
    depar: this.depar,
    localidad: this.localidad,
    contacto: this.contacto,
    fotos: this.fotos,
    termino: this.termino,
    datos: this.datos,
    novedades: this.novedades,
  });

  uploadImage($event: any) {
    const file = $event.target.files[0];
    console.log(file);

    const imgRef = ref(this.storage, `images/${file.name}`);
    this.imgreferent = imgRef;

    uploadBytes(imgRef, file)
      .then((response) => {
        console.log(response);
        this.getImages();
      })
      .catch((error) => console.error(error));
  }

  getImages() {
    const imagesRef = ref(this.storage, `images`);

    listAll(imagesRef)
      .then(async (response) => {
        for (let item of response.items) {
          if (item.fullPath === this.imgreferent.fullPath) {
            const url = await getDownloadURL(item);
            this.images.push(url);
            console.log(this.images);
          }
        }
      })
      .catch((error) => console.log(error));
  }

  registerPet() {
    this.form.value.localidad = this.form.value.pais + ', ';
    this.form.value.localidad =
      this.form.value.localidad + this.form.value.estado + ', ';
    this.form.value.localidad =
      this.form.value.localidad + this.form.value.depar + ' ';
    this.form.value.fotos = this.images;
    delete this.form.value.pais;
    delete this.form.value.estado;
    delete this.form.value.depar;
    delete this.form.value.termino;
    delete this.form.value.datos;
    delete this.form.value.novedades;
    console.log(this.form.value);
    this.petallService.create(<CreateMascota>this.form.value).subscribe(
      (res) => {
        alert('se registro exitosamente');
        this.router.navigate(['home']);
      },
      (err) => {
        alert('ocurrio un error');
      }
    );
  }
}
