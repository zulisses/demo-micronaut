variable "region" {
  type        = string
  description = "AWS region for the VPC and RDS instance."
  default     = "us-east-2"
}

variable "project_name" {
  type        = string
  description = "Prefix used in resource names and tags."
  default     = "demo-micronaut"
}

variable "vpc_cidr" {
  type        = string
  description = "CIDR block for the dedicated VPC."
  default     = "10.0.0.0/16"
}

variable "db_name" {
  type        = string
  description = "Initial PostgreSQL database name."
  default     = "demo"
}

variable "db_username" {
  type        = string
  description = "Master username. RDS for PostgreSQL does not allow 'postgres'."
  default     = "demo_admin"

  validation {
    condition     = var.db_username != "postgres"
    error_message = "RDS for PostgreSQL reserved the username 'postgres'. Use another name, such as demo_admin."
  }
}

variable "engine_version" {
  type        = string
  description = "PostgreSQL major version. RDS selects the latest compatible minor."
  default     = "17"
}

variable "instance_class" {
  type        = string
  description = "RDS instance class. db.t4g.micro is a low-cost Graviton option."
  default     = "db.t4g.micro"
}

variable "allocated_storage" {
  type        = number
  description = "Allocated storage in GiB."
  default     = 20
}

variable "publicly_accessible" {
  type        = bool
  description = "Expose a public endpoint so the local Micronaut app can connect. Keep false in production."
  default     = true
}

variable "allowed_cidr" {
  type        = string
  description = "CIDR allowed to reach PostgreSQL on 5432. Use your public IP with /32."
}

variable "skip_final_snapshot" {
  type        = bool
  description = "Skip the final snapshot on destroy. Convenient for demos; keep false in production."
  default     = true
}
