output "rds_endpoint" {
  description = "Hostname of the RDS instance."
  value       = aws_db_instance.this.address
}

output "rds_port" {
  description = "PostgreSQL port."
  value       = aws_db_instance.this.port
}

output "db_name" {
  description = "Initial database name."
  value       = aws_db_instance.this.db_name
}

output "db_username" {
  description = "Master username."
  value       = aws_db_instance.this.username
}

output "jdbc_url" {
  description = "JDBC URL for Micronaut. Password comes from Secrets Manager."
  value       = "jdbc:postgresql://${aws_db_instance.this.address}:${aws_db_instance.this.port}/${aws_db_instance.this.db_name}"
}

output "master_user_secret_arn" {
  description = "Secrets Manager ARN for the RDS-managed master password."
  value       = aws_db_instance.this.master_user_secret[0].secret_arn
}

output "alb_dns_name" {
  description = "Public DNS name of the application load balancer."
  value       = aws_lb.this.dns_name
}

output "ecs_cluster_name" {
  description = "Name of the ECS cluster."
  value       = aws_ecs_cluster.this.name
}

output "ecr_repository_url" {
  description = "ECR repository URL. push-ecr.sh appends the tag; that full value is container_image."
  value       = aws_ecr_repository.app.repository_url
}
