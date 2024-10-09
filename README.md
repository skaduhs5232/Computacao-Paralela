# Trabalho Prático - Computação Paralela

## Alunos Thiago e Pedro

## Discussão do Código

### Multiplicação da Matriz
O código utiliza threads para paralelizar a multiplicação da matriz, melhorando significativamente o desempenho em comparação com uma execução sequencial. Cada thread é responsável por calcular uma linha da matriz resultante \( A^2 \), e o tempo de execução de cada thread é medido e exibido individualmente. Essa abordagem permite a utilização eficiente dos recursos do sistema, especialmente em matrizes maiores.

### Tempo Total de Cálculo
Após a conclusão da multiplicação, o tempo total para calcular a matriz resultante é exibido. Isso possibilita uma comparação direta com as outras operações realizadas no código, fornecendo uma visão clara do desempenho da multiplicação em relação ao tempo total gasto nas outras tarefas.

### Encontrar Amigos em Comum
O cálculo para encontrar as duas pessoas com mais amigos em comum é realizado somando os elementos de cada linha da matriz resultante \( A^2 \). O tempo gasto nessa operação também é medido, permitindo avaliar a eficiência desse cálculo em relação à multiplicação da matriz. Os resultados indicam as duas pessoas que compartilham a maior quantidade de amigos na rede.

### Pessoa Mais Influente
A busca pela pessoa mais influente do grupo é feita somando os elementos de cada coluna da matriz resultante. Esse cálculo mede a influência de cada pessoa na rede, e o tempo para encontrar a pessoa mais influente também é registrado. Essa métrica fornece insights sobre a dinâmica social representada na matriz, identificando quem tem maior alcance entre os amigos.

## Considerações Finais
A implementação da multiplicação de matriz com threads demonstra uma aplicação prática de computação paralela, destacando seus benefícios em termos de tempo e eficiência. Os resultados obtidos não apenas ilustram a capacidade do código em lidar com operações complexas, mas também oferecem uma visão valiosa sobre a estrutura de conexões sociais representadas na matriz.
