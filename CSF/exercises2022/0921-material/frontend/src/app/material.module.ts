import { NgModule } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';


const matModules: any[] = [
    MatToolbarModule, MatButtonModule, 
    MatIconModule, MatInputModule, 
    MatFormFieldModule, MatSelectModule, 
    MatSidenavModule, MatListModule,
]

@NgModule({
    imports: matModules,
    exports: matModules
})

export class MaterialModule{}