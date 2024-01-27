provider "aws" {
  region = "us-east-1"
}

resource "aws_ecs_cluster" "tcfinanceiro_cluster" {
  name = "tcfinanceiro_cluster"
}

resource "aws_ecs_task_definition" "tcfinanceiro_task" {
  family                   = "tcfinanceiro_task"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "256"
  memory                   = "512"

  container_definitions = jsonencode(
    [
      {
        name : "tcfinanceiro-container",
        image : "tc-financeiro:v1.0",
        cpu : 256,
        memory : 512,
        essential : true,
        portMappings : [
          {
            containerPort : 3000,
            hostPort : 3000
          }
        ]
      }
    ]
  )
}

resource "aws_ecs_service" "tcfinanceiro_service" {
  name            = "tcfinanceiro-service"
  cluster         = aws_ecs_cluster.tcfinanceiro_cluster.id
  task_definition = aws_ecs_task_definition.tcfinanceiro_task.arn
  launch_type     = "FARGATE"

  network_configuration {
    subnets         = ["subnet-09d605cb6268bff9c", "subnet-0e9e7daad0b8b5c71"]
    security_groups = ["sg-0f0362717e74f8493"]
  }
}

resource "aws_iam_role" "ecs_execution_role-tc-financeiro" {
  name = "ecs_execution_role-tc-financeiro"

  assume_role_policy = jsonencode({
    Version   = "2012-10-17",
    Statement = [
      {
        Action    = "sts:AssumeRole",
        Effect    = "Allow",
        Principal = {
          Service = "ecs-tasks.amazonaws.com"
        }
      }
    ]
  })
}