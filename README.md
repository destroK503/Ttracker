# Ttracker

> [!NOTE]
>
> This is a personal project however it was designed
> to be easily expanded or personalized to suit any kind
> of personal requiremt 

Simple Task tracker written in java for portability


# Building & getting going ✈️

go into the project and run `grandle build`
then inside the dir `Ttracker\build\libs` you will find the 
`.jar` file after that you can add a wrapper into either your
`.zshrc` file or your `ps1` file 

here an example for windows

```ps1
function Func_tracker {
    $jarPath = "C:\DEV\java\practices\Ttracker\build\libs\Ttracker.jar"

    if (-Not (Test-Path $jarPath)) {
        Write-Error "Ttracker.jar could not been found"
        return
    }

    try {
        java -jar $jarPath @args
    } catch {
        Write-Error "Could not execute `.jar` see error: $_"
    }
}

New-Alias -Name 'tracker' -Value 'Func_tracker' -Scope Global
```


> [!WARNING]
>
> This is work in progress still
>
>
