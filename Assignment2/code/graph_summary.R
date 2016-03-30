library(ggplot2)
library(reshape)
#score <- read.csv('/Users/craiglab/Documents/ml/knapsack1.csv', header = F)
#score <- read.csv('/Users/craiglab/Documents/ml/tsm.csv', header = F)
score <- read.csv('/Users/craiglab/Documents/ml/nqueen.csv', header = F)
score
# transpose data frame
score <- t(score)
score
colnames(score) <- c('iter', 'RHC', 'SA', 'GA', 'MIMIC')
score
rownames(score) <- score[,'iter']
score
score <- subset(score, select = -c(iter))
score
score <- melt(score, id.vars = 'iter', variable.names = 'algo')
score
colnames(score) <- c('iter', 'algo', 'value')
score
#ggplot(score, aes(iter, value)) + geom_line(aes(colour = algo)) + ggtitle('knapsackTest')
#ggplot(score, aes(iter, value)) + geom_line(aes(colour = algo)) + ggtitle('TravelSalesMan')
ggplot(score, aes(iter, value)) + geom_line(aes(colour = algo)) + ggtitle('NqueenTest')

### knapsackTest
knap <- read.csv('/Users/craiglab/Documents/ml/Assignment2/knapsack_result.csv', header = T)
head(knap)
# transpose data frame
knap <- subset(knap, select = c(RHC.SCORE, SA.SCORE, GA.SCORE, MIMIC.SCORE))
head(knap)
tail(knap)
knap$iter <- seq(1, 50)
knap <- melt(knap, id.vars = 'iter', variable.names = 'algo')
head(knap)
colnames(knap) <- c('iter', 'algo', 'value')
head(knap)
#ggplot(score, aes(iter, value)) + geom_line(aes(colour = algo)) + ggtitle('knapsackTest')
#ggplot(score, aes(iter, value)) + geom_line(aes(colour = algo)) + ggtitle('TravelSalesMan')
ggplot(knap, aes(iter, value)) + geom_line(aes(colour = algo)) + ggtitle('knapsackTest')

### travelingSalesmen Test
tsm <- read.csv('/Users/craiglab/Documents/ml/Assignment2/tsm/travelingsalesman.csv', header = T)
head(tsm)
# transpose data frame
tsm <- subset(tsm, select = c(RHC.SCORE, SA.SCORE, GA.SCORE, MIMIC.SCORE))
head(tsm)
tail(tsm)
tsm$iter <- seq(1, 30)
tsm<- melt(tsm, id.vars = 'iter', variable.names = 'algo')
head(tsm)
colnames(tsm) <- c('iter', 'algo', 'value')
head(tsm)
#ggplot(score, aes(iter, value)) + geom_line(aes(colour = algo)) + ggtitle('knapsackTest')
#ggplot(score, aes(iter, value)) + geom_line(aes(colour = algo)) + ggtitle('TravelSalesMan')
ggplot(tsm, aes(iter, value)) + geom_line(aes(colour = algo)) + ggtitle('traveling Salesman')


### nqueens Test
nqueens <- read.csv('/Users/craiglab/Documents/ml/Assignment2/nqueens/nqueens_Results.csv', header = T)
head(nqueens)

nqueens <- subset(nqueens, select = c(RHC.SCORE, SA.SCORE, GA.SCORE, MIMIC.SCORE))
head(nqueens)
tail(nqueens)
nqueens$iter <- seq(1, 100)
nqueens <- melt(nqueens, id.vars = 'iter', variable.names = 'algo')
head(nqueens)
colnames(nqueens) <- c('iter', 'algo', 'value')
head(nqueens)
#ggplot(score, aes(iter, value)) + geom_line(aes(colour = algo)) + ggtitle('knapsackTest')
#ggplot(score, aes(iter, value)) + geom_line(aes(colour = algo)) + ggtitle('TravelSalesMan')
ggplot(nqueens, aes(iter, value)) + geom_line(aes(colour = algo)) + ggtitle('NQueens')



