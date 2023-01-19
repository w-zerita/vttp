import { NgModule } from '@angular/core';
import {MatToolbarModule} from '@angular/material/toolbar';

const matModules: any[] = [
    MatToolbarModule
]

@NgModule({
    imports: matModules,
    exports: matModules
})

export class MaterialModule{}