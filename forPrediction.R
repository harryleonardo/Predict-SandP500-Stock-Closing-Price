neuralNetFix=function(){
	trainingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\training50.csv")
	testingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\testing50.csv")
	
	X1training <- trainingData$id
	X2training <- trainingData$open
	X3training <- trainingData$high
	X4training <- trainingData$low
	X5training <- trainingData$close
	targetTraining <- trainingData$volume

	X1testing <- testingData$open
	X2testing <- testingData$high
	X3testing <- testingData$low
	X4testing <- testingData$close
	X5testing <- testingData$volume
	targetTesting <- testingData$target
		
	xTraining <- cbind(X1training,X2training,X3training,X4training,X5training)

	sum.trainingData <- data.frame(xTraining,targetTraining)

	netTraining <- neuralnet(targetTraining~X1training+X2training+X3training+X4training+X5training, sum.trainingData, hidden=5,err.fct = "sse",act.fct="logistic",threshold=0.01)
	
	data = netTraining$net.result

	write.csv(data, file = "D:\\Kuliah\\Semester VI\\TAII\\modelTraining50dailyData.csv")

	xTesting <- cbind(X1testing,X2testing,X3testing,X4testing,X5testing)
	
	sum.testingData <- data.frame(xTesting,targetTesting)
	
	result <- compute(netTraining,sum.testingData[,1:5])

	return(result$net.result[10,1])
}

neuralNetFixMonthly=function(){
	trainingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\training10Month.csv")
	testingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\testing10Month.csv")
	
	X1training <- trainingData$id
	X2training <- trainingData$open
	X3training <- trainingData$high
	X4training <- trainingData$low
	X5training <- trainingData$close
	targetTraining <- trainingData$volume

	X1testing <- testingData$open
	X2testing <- testingData$high
	X3testing <- testingData$low
	X4testing <- testingData$close
	X5testing <- testingData$volume
	targetTesting <- testingData$target

	xTraining <- cbind(X1training,X2training,X3training,X4training,X5training)

	sum.trainingData <- data.frame(xTraining,targetTraining)

	netTraining <- neuralnet(targetTraining~X1training+X2training+X3training+X4training+X5training, sum.trainingData, hidden=5,err.fct = "sse",act.fct="logistic",stepmax = 1000,threshold=0.01)
	
	data = netTraining$net.result

	write.csv(data, file = "D:\\Kuliah\\Semester VI\\TAII\\modelTraining10DataMonth.csv")

	xTesting <- cbind(X1testing,X2testing,X3testing,X4testing,X5testing)
	
	sum.testingData <- data.frame(xTesting,targetTesting)
	
	result <- compute(netTraining,sum.testingData[,1:5])

	return(result$net.result[2,1])
}