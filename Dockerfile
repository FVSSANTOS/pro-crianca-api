FROM mcr.microsoft.com/mssql/server:latest

# Instale os pré-requisitos para o mssql-tools
RUN apt-get update \
    && apt-get install -y curl gnupg \
    && curl https://packages.microsoft.com/keys/microsoft.asc | apt-key add - \
    && curl https://packages.microsoft.com/config/ubuntu/$(lsb_release -rs)/prod.list > /etc/apt/sources.list.d/mssql-release.list \
    && apt-get update

# Instale o pacote do mssql-tools
RUN ACCEPT_EULA=Y apt-get install -y mssql-tools

# Adicione o diretório que contém as ferramentas ao PATH
ENV PATH="${PATH}:/opt/mssql-tools/bin"

# Comando de entrada padrão (pode ser substituído)
CMD ["/opt/mssql/bin/sqlservr"]
