

/*
def loadConf(pathToConf: String): Config = {
  val path = new Path(pathToConf)
  val confFile = File.createTempFile(path.getName, "tmp")
  confFile.deleteOnExit()
  getFileSystemByUri(path.toUri).copyToLocalFile(path, new Path(confFile.getAbsolutePath))

  ConfigFactory.load(ConfigFactory.parseFile(confFile))
}
Configuration hdfsConf = new Configuration()
FileSystem fs = FileSystem.get(hdfsConf)

val hdfsConf = new Configuration()
val fs = FileSystem.get(hdfsConf)

//THe file name contains absolute path of file
val is = fs.open(new Path())
val prop = new Nothing */