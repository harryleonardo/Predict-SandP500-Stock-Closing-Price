neuralNet10=function(){
	testingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\testing10.csv")
	trainingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\training10.csv")
	
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

	write.csv(data, file = "D:\\Kuliah\\Semester VI\\TAII\\modelTraining10dailyData.csv")

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

	netTraining <- neuralnet(targetTraining~X1training+X2training+X3training+X4training+X5training, sum.trainingData, hidden=5,err.fct = "sse",act.fct="logistic",stepmax = 1000,threshold=0.01)
	
	data = netTraining$net.result

	write.csv(data, file = "D:\\Kuliah\\Semester VI\\TAII\\modelTraining50dailyData.csv")

	xTesting <- cbind(X1testing,X2testing,X3testing,X4testing,X5testing)
	
	sum.testingData <- data.frame(xTesting,targetTesting)
	
	result <- compute(netTraining,sum.testingData[,1:5])

	return(result$net.result)
}

neuralNet100=function(){	
	trainingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\training100.csv")
	testingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\testing100.csv")
	
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

	write.csv(data, file = "D:\\Kuliah\\Semester VI\\TAII\\modelTraining100dailyData.csv")

	xTesting <- cbind(X1testing,X2testing,X3testing,X4testing,X5testing)
	
	sum.testingData <- data.frame(xTesting,targetTesting)
	
	result <- compute(netTraining,sum.testingData[,1:5])

	return(result$net.result)
}

neuralNet500=function(){	
	trainingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\training500.csv")
	testingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\testing500.csv")
	
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

	netTraining <- neuralnet(targetTraining~X1training+X2training+X3training+X4training+X5training, sum.trainingData, hidden=5,err.fct = "sse",act.fct="logistic",stepmax = 10000,threshold=0.01)
	
	data = netTraining$net.result

	write.csv(data, file = "D:\\Kuliah\\Semester VI\\TAII\\modelTraining500dailyData.csv")

	xTesting <- cbind(X1testing,X2testing,X3testing,X4testing,X5testing)
	
	sum.testingData <- data.frame(xTesting,targetTesting)
	
	result <- compute(netTraining,sum.testingData[,1:5])

	return(result$net.result)
}

neuralNet2500=function(){	
	trainingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\training2500.csv")
	testingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\testing2500.csv")
	
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

	netTraining <- neuralnet(targetTraining~X1training+X2training+X3training+X4training+X5training, sum.trainingData, hidden=5,err.fct = "sse",act.fct="logistic",stepmax = 10000,threshold=0.01)
	
	data = netTraining$net.result

	write.csv(data, file = "D:\\Kuliah\\Semester VI\\TAII\\modelTraining2500dailyData.csv")

	xTesting <- cbind(X1testing,X2testing,X3testing,X4testing,X5testing)
	
	sum.testingData <- data.frame(xTesting,targetTesting)
	
	result <- compute(netTraining,sum.testingData[,1:5])

	return(result$net.result)
}

neuralNet10Month=function(){
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

	return(result$net.result)
}

neuralNet50Month=function(){	
	trainingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\training50Month.csv")
	testingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\testing50Month.csv")
	
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

	write.csv(data, file = "D:\\Kuliah\\Semester VI\\TAII\\modelTraining50DataMonth.csv")

	xTesting <- cbind(X1testing,X2testing,X3testing,X4testing,X5testing)
	
	sum.testingData <- data.frame(xTesting,targetTesting)
	
	result <- compute(netTraining,sum.testingData[,1:5])

	return(result$net.result)
}

neuralNet120Month=function(){
	trainingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\training120Month.csv")
	testingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\testing120Month.csv")
	
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

	write.csv(data, file = "D:\\Kuliah\\Semester VI\\TAII\\modelTraining120DataMonth.csv")

	xTesting <- cbind(X1testing,X2testing,X3testing,X4testing,X5testing)
	
	sum.testingData <- data.frame(xTesting,targetTesting)
	
	result <- compute(netTraining,sum.testingData[,1:5])

	return(result$net.result)
}

neuralNet10MonthSecond=function(){
	trainingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\training10MonthSecond.csv")
	testingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\testing10MonthSecond.csv")
	
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

	write.csv(data, file = "D:\\Kuliah\\Semester VI\\TAII\\modelTraining10DataMonthSecond.csv")

	xTesting <- cbind(X1testing,X2testing,X3testing,X4testing,X5testing)
	
	sum.testingData <- data.frame(xTesting,targetTesting)
	
	result <- compute(netTraining,sum.testingData[,1:5])

	return(result$net.result)
}

neuralNet50MonthSecond=function(){	
	trainingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\training50MonthSecond.csv")
	testingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\testing50MonthSecond.csv")
	
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

	write.csv(data, file = "D:\\Kuliah\\Semester VI\\TAII\\modelTraining50DataMonthSecond.csv")

	xTesting <- cbind(X1testing,X2testing,X3testing,X4testing,X5testing)
	
	sum.testingData <- data.frame(xTesting,targetTesting)
	
	result <- compute(netTraining,sum.testingData[,1:5])

	return(result$net.result)
}

neuralNet120MonthSecond=function(){
	trainingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\training120MonthSecond.csv")
	testingData = read.csv("D:\\Kuliah\\Semester VI\\TAII\\testing120MonthSecond.csv")
	
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

	write.csv(data, file = "D:\\Kuliah\\Semester VI\\TAII\\modelTraining120DataMonthSecond.csv")

	xTesting <- cbind(X1testing,X2testing,X3testing,X4testing,X5testing)
	
	sum.testingData <- data.frame(xTesting,targetTesting)
	
	result <- compute(netTraining,sum.testingData[,1:5])

	return(result$net.result)
}