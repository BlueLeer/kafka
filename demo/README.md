## SpringBoot和Kafka整合，实现了一个简单的日志处理demo

<p align="center">
<a href="#SpringBoot"><img src="https://img.shields.io/badge/-SpringBoot-brightgreen"></a>
<a href="#Kafka"><img src="https://img.shields.io/badge/-Kafka-yellowgreen"></a>
<a href="#AOP"><img src="https://img.shields.io/badge/-AOP-blue"></a>
</p>

场景：高并发情况下，用户访问量大，产生的日志量大，通过消息队列，异步
的对日志数据进行入库或者数据分析（日志数据处理-未实现，日志数据入库-未实现）