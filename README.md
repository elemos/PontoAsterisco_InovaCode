# PontoAsterisco_InovaCode

A equipe PontoAsterisco apresenta o projeto do InovaCode 2020

Resumo:
    A solução pensada, dentro dos temas sugeridos (desemprego e Homeoffice), foi a criação de um projeto voltado para o auxilio da implementação conjunta e suporte a código entre colegas de uma mesma empresa. 
    Dessa maneira visualizou-se a headline que define a solução:
        Criar um ambiente online, voltado para a criação e edição de códigos fontes, permitindo o acesso simultaneo de uma ou mais pessoas de uma equipe, com o intuito de facilitar a cooperação e suporte em casos de duvidas entre membros da equipe de desenvolvimento. 
    Para que isso se torne realidade é necessário três pontos chaves: (1)Um ambiente online compartilhado para edição de textos (Códigos fontes), (2)uma maneira de exportar arquivos de código fontes locais para a plataforma online, a fim de permitir o compartilhamento, e por fim, (3)uma interface voltada para o desenvolvimento (Simulação de uma IDE completa).
    Dessa maneira para criar um objeto de exemplificação, nesse curto periodo de tempo,  do projeto que foi idealizado foi utilizado o Etherpad-lite para suprir o ponto chave 1, foi criado um plugin no netbeans que envia o arquivo .java em foco na IDE para o Etherpad-lite para suprir o ponto chave 2, e por fim foi utilizado um plugin dentro do etherpad-lite que traz um suporte minimo a programação em diversas linguagens para o ponto chave 3.
    
    
Colocando para funcionar:
    1°: É necessário primeiramente iniciar o servidor com o etherpad-lite. (necessário ter o node.js instalado)
        Linux (Pelo prompt)
            - Abra a pasta etherpad-lite
            - Abra a pasta bin
            - Executar o run.sh
            - Para ver se está tudo ok abra:  http://127.0.0.1:9001 no navegador.
        Windows
            - Execute o start.bat com dois cliques ou pelo cmd, ele esta na pasta raiz do etherpa
    2°: Abra o NetBeans e instale o plugin org-pa.nbm (Plugin está localizado em /nb-plugin/build)
    3°: Abra um arquivo no NetBeans (Java, JavaScript, HTML)
    4°: Clique no novo icone que apareceu no taskbar do Netbeans (Duas cabeças ligadas por setas)
    5°: Veja o arquivo abrir no etherpad, ao fechar a aba do etherpad o arquivo será atualizado no netbeans.
    OBS: Como o projeto é um protótipo para demosntração o plugin do netbeans está com o endereço setado do servidor local direto em código fonte (Enviado em anexo);
    
    
Vale resaltar que pelo curto espaço de tempo foi optado por utilizar implementações prontas para fazer a prova de conceito. 



Link para projeto original do Etherpad: https://github.com/ether/etherpad-lite
etherpad utiliza licença apache v2.0