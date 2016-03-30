# graph the temperature change for knapsack SA algorithm
knapTemp <- read.csv('/Users/craiglab/Documents/ml/Assignment2/2016-03-12-13-53-51_Results.csv', header = T)
head(knapTemp)
ggplot(knapTemp, aes(TEMPERATURE, SA.SCORE)) + geom_line() + ggtitle('KnapsackTest SA changing temperature')

# graph the cooling rate for knapsack SA algorithm
knapCoolRate <- read.csv('/Users/craiglab/Documents/ml/Assignment2/2016-03-12-13-27-22_Results.csv', header = T)
head(knapCoolRate)
ggplot(knapCoolRate, aes(COOLING.RATE, SA.SCORE)) + geom_line() + ggtitle('KnapsackTest changing cooling rate')

# graph the population for knapsack GA algorithm
knapPopulation <- read.csv('/Users/craiglab/Documents/ml/Assignment2/2016-03-12-11-58-55_Results.csv', header = T)
head(knapPopulation)
ggplot(knapPopulation, aes(POPULATION, GA.SCORE)) + geom_line() + ggtitle('KnapsackTest GA population')

# graph the tomate for knapsack GA algorithm
knapTomate <- read.csv('/Users/craiglab/Documents/ml/Assignment2/2016-03-12-12-10-12_Results.csv', header = T)
head(knapTomate)
ggplot(knapTomate, aes(TOMATE, GA.SCORE)) + geom_line() + ggtitle('KnapsackTest GA tomate')

# graph the tomutate for knapsack GA algorithm
knapTomutate <- read.csv('/Users/craiglab/Documents/ml/Assignment2/2016-03-12-12-20-59_Results.csv', header = T)
head(knapTomutate)
ggplot(knapTomutate, aes(TOMUTATE, GA.SCORE)) + geom_line() + ggtitle('KnapsackTest GA tomutate')

# graph the sample for knapsack MIMIC algorithm
knapsample <- read.csv('/Users/craiglab/Documents/ml/Assignment2/2016-03-12-12-54-52_Results.csv', header = T)
head(knapsample)
ggplot(knapsample, aes(SAMPLE, MIMIC.SCORE)) + geom_line() + ggtitle('KnapsackTest MIMIC sample')

# graph the tokeep for knapsack MIMIC algorithm
knaptokeep <- read.csv('/Users/craiglab/Documents/ml/Assignment2/2016-03-12-13-12-05_Results.csv', header = T)
head(knaptokeep)
ggplot(knaptokeep, aes(TOKEEP, MIMIC.SCORE)) + geom_line() + ggtitle('KnapsackTest MIMIC tokeep')

# graph the temperature change for travelingSalesman SA algorithm
TSMTemp <- read.csv('/Users/craiglab/Documents/ml/Assignment2/2016-03-12-18-25-32_Results.csv', header = T)
head(TSMTemp)
ggplot(TSMTemp, aes(log10(TEMPERATURE), SA.SCORE)) + geom_line() + ggtitle('TravelingSalesman SA changing temperature')

# graph the cooling rate for travelingSalesman SA algorithm
TSMCoolRate <- read.csv('/Users/craiglab/Documents/ml/Assignment2/2016-03-12-18-31-33_Results.csv', header = T)
head(TSMCoolRate)
ggplot(TSMCoolRate, aes(COOLING.RATE, SA.SCORE)) + geom_line() + ggtitle('TravelingSalesman SA changing cooling rate')

# graph the population for travelingSalesman GA algorithm
TSMPopulation <- read.csv('/Users/craiglab/Documents/ml/Assignment2/2016-03-12-18-48-45_Results.csv', header = T)
head(TSMPopulation)
ggplot(TSMPopulation, aes(POPULATION, GA.SCORE)) + geom_line() + ggtitle('TravelingSalesman GA population')

# graph the tomate for travelingSalesman GA algorithm
TSMTomate <- read.csv('/Users/craiglab/Documents/ml/Assignment2/2016-03-12-19-00-33_Results.csv', header = T)
head(TSMTomate)
ggplot(TSMTomate, aes(TOMATE, GA.SCORE)) + geom_line() + ggtitle('TravelingSalesman GA tomate')

# graph the tomutate for travelingSalesman GA algorithm
TSMTomutate <- read.csv('/Users/craiglab/Documents/ml/Assignment2/2016-03-12-19-07-17_Results.csv', header = T)
head(TSMTomutate)
ggplot(TSMTomutate, aes(TOMUTATE, GA.SCORE)) + geom_line() + ggtitle('travelingSalesman GA tomutate')

# graph the sample for travelingSalesman MIMIC algorithm
TSMsample <- read.csv('/Users/craiglab/Documents/ml/Assignment2/2016-03-12-19-27-24_Results.csv', header = T)
head(TSMsample)
ggplot(TSMsample, aes(SAMPLE, MIMIC.SCORE)) + geom_line() + ggtitle('travelingSalesman MIMIC sample')

# graph the tokeep for travelingSalesman MIMIC algorithm
TSMtokeep <- read.csv('/Users/craiglab/Documents/ml/Assignment2/2016-03-12-19-41-19_Results.csv', header = T)
head(TSMtokeep)
ggplot(TSMtokeep, aes(TOKEEP, MIMIC.SCORE)) + geom_line() + ggtitle('travelingSalesman MIMIC tokeep')

# graph the temperature change for nqueens SA algorithm
nQueensTemp <- read.csv('/Users/craiglab/Documents/ml/Assignment2/nqueens/2016-03-12-20-34-41_Results.csv', header = T)
head(nQueensTemp)
ggplot(nQueensTemp, aes(TEMPERATURE, SA.SCORE)) + geom_line() + ggtitle('NQueens SA changing temperature')

# graph the cooling rate for nqueens SA algorithm
nQueensCoolRate <- read.csv('/Users/craiglab/Documents/ml/Assignment2/nqueens/2016-03-12-20-43-03_Results.csv', header = T)
head(nQueensCoolRate)
ggplot(nQueensCoolRate, aes(COOLING.RATE, SA.SCORE)) + geom_line() + ggtitle('NQueens SA changing cooling rate')

# graph the population for nqueens GA algorithm
nQueensPopulation <- read.csv('/Users/craiglab/Documents/ml/Assignment2/nqueens/2016-03-12-20-52-18_Results.csv', header = T)
head(nQueensPopulation)
ggplot(nQueensPopulation, aes(POPULATION, GA.SCORE)) + geom_line() + ggtitle('NQueens GA population')

# graph the tomate for nqueens GA algorithm
nQueensTomate <- read.csv('/Users/craiglab/Documents/ml/Assignment2/nqueens/2016-03-12-21-02-42_Results.csv', header = T)
head(nQueensTomate)
ggplot(nQueensTomate, aes(TOMATE, GA.SCORE)) + geom_line() + ggtitle('NQueens GA tomate')

# graph the tomutate for nqueens GA algorithm
nQueensTomutate <- read.csv('/Users/craiglab/Documents/ml/Assignment2/nqueens/2016-03-12-21-46-36_Results.csv', header = T)
head(nQueensTomutate)
ggplot(nQueensTomutate, aes(TOMUTATE, GA.SCORE)) + geom_line() + ggtitle('NQueens GA tomutate')

# graph the sample for nqueens MIMIC algorithm
nQueensSample <- read.csv('/Users/craiglab/Documents/ml/Assignment2/nqueens/2016-03-12-21-57-24_Results.csv', header = T)
head(nQueensSample)
ggplot(nQueensSample, aes(SAMPLE, MIMIC.SCORE)) + geom_line() + ggtitle('NQueens MIMIC sample')

# graph the tokeep for nqueens MIMIC algorithm
nQueensTokeep <- read.csv('/Users/craiglab/Documents/ml/Assignment2/nqueens/2016-03-12-22-09-50_Results.csv', header = T)
head(nQueensTokeep)
ggplot(nQueensTokeep, aes(TOKEEP, MIMIC.SCORE)) + geom_line() + ggtitle('NQueens MIMIC tokeep')













