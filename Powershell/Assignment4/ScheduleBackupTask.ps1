# Create a new task action
$taskAction = New-ScheduledTaskAction `
    -Execute 'powershell.exe' `
    -Argument '-File C:\Assignment4\BackupMyFiles.ps1 C:\ClassWork C:\Temp\_Backup 3' 
$taskAction

# Create a new trigger (Every 12 hours)
$taskTrigger = New-ScheduledTaskTrigger -Once -At (Get-Date) -RepetitionInterval (New-TimeSpan -Hours 12)

# Register the new PowerShell scheduled task

# The name of your scheduled task.
$taskName = "Scheduled Backup"

# Describe the scheduled task.
$description = "Back up files every 12 hours."

# Register the scheduled task
Register-ScheduledTask `
    -TaskName $taskName `
    -Action $taskAction `
    -Trigger $taskTrigger `
    -Description $description

Start-ScheduledTask -TaskName $taskName

# Get the scheduled task information
#Get-ScheduledTaskInfo -TaskName $taskName

# Unregister the scheduled task 
#Unregister-ScheduledTask -TaskName $taskName -Confirm:$false