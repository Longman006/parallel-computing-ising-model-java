library(ggplot2)

g<-ggplot(IsingTodM, aes(x =T )) +
  geom_point(aes(y = IsingTodM$M, color = "temp"))+
  geom_line(aes(y = IsingTodM$M, color = "temp"),alpha=0.25) +
  scale_colour_manual("", breaks = c("temp", "cos"), values = c("blue", "red"))+
  geom_smooth(aes(y = IsingTodM$M, color = "temp"), fill="blue", alpha=0.2)

g2<-ggplot(`IsingHodMT=0.05`, aes(x=H,y=M )) +
  geom_point(aes(y = M, color = "temp"))+
  geom_line(aes(y = M, color = "temp"),alpha=0.25) +
  scale_colour_manual("", breaks = c("temp", "cos"), values = c("red", "red"))
