myneuralnetscript=function(){	
	testingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\testing10.csv")
	trainingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\training10.csv")
	
	X1training <- trainingData$open
	X2training <- trainingData$high
	X3training <- trainingData$low
	X4training <- trainingData$close
	X5training <- trainingData$volume
	targetTraining <- trainingData$target

	X1testing <- testingData$open
	X2testing <- testingData$high
	X3testing <- testingData$low
	X4testing <- testingData$close

	X5testing <- testingData$volume
	targetTesting <- testingData$target
		
	xTraining <- cbind(X1training,X2training,X3training,X4training,X5training)

	sum.trainingData <- data.frame(xTraining,targetTraining)

	netTraining <- neuralnet(targetTraining~X1training+X2training+X3training+X4training+X5training, sum.trainingData, hidden=5,err.fct = "sse",stepmax = 1000,act.fct="logistic")
	
	xTesting <- cbind(X1testing,X2testing,X3testing,X4testing,X5testing)
	
	sum.testingData <- data.frame(xTesting,targetTesting)
	
	result <- compute(netTraining,sum.testingData[,1:5])

	return(result$net.result)
}

neuralNet50=function(){
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

	netTraining <- neuralnet(targetTraining~X1training+X2training+X3training+X4training+X5training, sum.trainingData, hidden=5,err.fct = "sse",act.fct="logistic",threshold=0.001)

	xTesting <- cbind(X1testing,X2testing,X3testing,X4testing,X5testing)
	
	sum.testingData <- data.frame(xTesting,targetTesting)
	
	result <- compute(netTraining,sum.testingData[,1:5])

	return(result$net.result)
}