## 事务机制
    启用事务：txSelect  txCommit  txRollback

- txSelect:用户将当前channel设置成transaction模式(开始事务)
- txCommit:用于提交事务（提交事务）
- txRollback:回滚事务


缺点：每次都要开始事务，提交事务等，降低了吞吐量。