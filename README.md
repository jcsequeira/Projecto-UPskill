# Projecto-UPskill

## Está divido em três Apps dentro do ProjetoIntegrador:

### ArtsyLoader: preenche a base de dados, TBD
### ExplorArt: App principal dos galeristas
### RestAPI: API que efetua as operaçoes CRUD na BD

#### Considerações/Ideias/Tarefas
- As PKs da BD estao com autoincrement, então não é necessário o codigo dos POSTs/Inserts inserirem este atributo.
- Implementar atributo isArtsy nas Classes Modelo (ObraArte, Artista, Evento, Galeria), inicializando no construtor como um int = 0.
- Implementar os POSTs/INSERTs, de modo a inserir o atributo isArtsy na tabela em conjunto com os restantes.
- A ideia é que os json provenientes da App ArtsyLoader, viram com o atributo isArtsy=1.

#### Considerações na BD MySQL:
- Artsy API no caso das Galerias, so tem o nome e o website, ponderar os restantes atributos da tabela para serem opcionais, fazer o mesmo para
as tabelas dos Aritstas/obras/Eventos.
- Implementar/Rever os ON DELETE, ON UPDATE, cascade/no action etc.

#### Classes Modelo devem ser iguais as tabelas da BD e ser as mesmas para todas as APPs
- sendo boa ideia talvez partilhas entre as 3 apps.
- Apesar de json Artistas, ter o Codigo_Pais em vez de nacionalidade, sera a propia ExplorArt 
a reslver essa parte, fazendo um get request a restAPI para saber qual a naciolidade daquele codigo,
antes de passar o perfil de um artista na GUI.

#### Ideias para popular a BD com os dados da Artsy API
- ArtsyLoader enviará um request com json para a RestAPI e será esta que fara os inserts.
- Faz sentido que se crie endpoitns propios para ArtsyLoader aceder, por exemplo:
POST: populate/artista/all, em que este endpoint recebe uma List de "todos" os artistas da artsy (lista de objectos artista)
em json, depois o controller chamar o addALLArtista do service, e o service chamar o metodo addArtista do ArtistaRepository para cada objeto dessa lista.


