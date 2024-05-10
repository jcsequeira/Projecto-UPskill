<h1 align="center" style="font-weight: bold;">Project-UPSkill 💻</h1>

<p align="center">
 <a href="#intro">Intro</a> • 
 <a href="#tech">Technologies</a> • 
 <a href="#started">Getting Started</a> • 
 <a href="#artsy">Artsy API</a> •
 <a href="#colab">Collaborators</a> •
</p>

<p align="left">
    <b>UPSikll 2023 - JAVA course final project: A Desktop JavaFX App Designed for Art Gallerys, with a Rest API implementation and a local MySQL database, also populated with real world data from the https://developers.artsy.net </b>
</p>

<h2 id="intro">Intro</h2>

This is a final course project, so is not optimized, what was asked:
Create a JavaFX Desktop App for a Gallerys to keep track of their Artwork, Shows, and maybe some extra funciolities.
Warning: The full App is the Portuguese Language, but maybe one day will be updated to English.
<br>
<h3>Our Approach Designing this project:</h3>
We decided
ArtsyLoader: preenche a base de dados, TBD
ExplorArt: App principal dos galeristas
RestAPI: API que efetua as operaçoes CRUD na BD



<h2 id="technologies">💻 Technologies</h2>

- Java for Backend
- JavaFX for Frontend
- MySQL for DB
- Rest API for CRUD operations on the DB
- SparkJava framework for the API

<h2 id="started">🚀 Getting started</h2>


<h3>Prerequisites</h3>

<h4>Follow the instructions below or watch de full video tutorial here: <br> 
 https://www.youtube.com/watch?v=Gui7ZIIuG9I</h4>

<h3>Install:</h3>

- [Github](https://github.com/git-guides/install-git)
- [IntelliJ Ideia Community](https://www.jetbrains.com/idea/download/download-thanks.html?platform=windows&code=IIC)
- [Java 17 liberica](https://bell-sw.com/pages/downloads/#jdk-17-lts)
- [MySQL Server](https://dev.mysql.com/downloads/installer/)
- [MySQL Workbench](https://dev.mysql.com/downloads/workbench/)
  
  
<h3>Cloning</h3>

After git installed, open windows powershell, cmd or GitBash, go to the desired directory by using "cd" command and type:

```bash
git clone https://github.com/jcsequeira/Projecto-UPskill.git
```

<h3>Setting up the MySQL Database</h2>

- Start MySQL Workbench and login into your local MySQL server, that you set up on MySQL installation.
- Copy the content on "[create.sql](https://github.com/jcsequeira/Projecto-UPskill/blob/master/create.sql)" and paste it on your Workbench script.
- Run the script and that should be it.


<h3>Starting</h3>

How to start the App:

- Open the project in IntelliJ, select the Liberica JDK 17.0.11 and reload maven pom.
- Run ExplorArtApp/src/main/java/ExplorArtApp.java.
- On first, run db is empty, you can create your own data using the app or import from ArtsyAPI.
- To Import data from Artsy API, Menu Admin->Importar Dados da Arsty API.
- To delete All Data Imported from Artsy API, Menu Admin->Limpar Dados da Arsty API.

<br>
<h2 id="artsy">📍 Artsy API</h2>

[Artsy API](https://developers.artsy.net/v2/) is an API to obtain data from the [https://www.artsy.net/](https://www.artsy.net/) website that has real world data, about Artworks, Artists, Gallerys, Events, Auctions and so on.
In this project, Artsy data was used to populate the App database, for testing purposes.



<h2 id="colab">🤝 Collaborators</h2>

Special thank you to my colleague and friend who co-lab in this project.

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/LRossa13">
        <img src="https://avatars.githubusercontent.com/u/144017970?v=4" width="100px;" /><br>
        <sub>
          <b>Luis Rossa</b>
        </sub>
      </a>
    </td>     
  </tr>
</table>
