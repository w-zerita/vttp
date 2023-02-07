import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'createXmlDoc';

  public generateXml(): void {
    // Create doc
    const xmlDoc = document.implementation.createDocument(null, "root", null);

    // Grab the root node.
    const rootNode = xmlDoc.querySelector("root");

    // Add a child element
    const simNode = xmlDoc.createElement("simulation");
    const simTimeNode = xmlDoc.createElement("max_sim_sec");
    const timeValue = xmlDoc.createTextNode("123");
    simTimeNode.appendChild(timeValue);
    simNode.appendChild(simTimeNode);
    rootNode?.appendChild(simNode);

    const envNode = this.createNodeFromObject(
      {
        gravity: 1,
        wind: 3,
        atmo: 98
      },
      xmlDoc,
      "environment"
    );

    rootNode?.appendChild(envNode);

    // Serialize to string
    const serializer = new XMLSerializer();
    const xmlString = serializer.serializeToString(xmlDoc);
    console.log(xmlString);
  }

  public createNodeFromObject(
    obj: any,
    xmlDoc: any,
    nodeName: string
  ): Element {
    return Object.keys(obj).reduce((parentNode, key) => {
      const node = xmlDoc.createElement(key);
      const value = xmlDoc.createTextNode(obj[key].toString());
      node.appendChild(value);
      parentNode.appendChild(node);
      return parentNode;
    }, xmlDoc.createElement(nodeName));
  }
}
