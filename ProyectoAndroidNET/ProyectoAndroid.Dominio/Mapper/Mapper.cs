using System;
using System.Collections.Generic;
using IBatisNet.Common;
using IBatisNet.Common.Utilities;
using IBatisNet.DataMapper;
using IBatisNet.DataMapper.Configuration;

namespace ProyectoAndroid.Dominio.Mapper
{
    public class Mapper
    {
        private static volatile ISqlMapper mapper = null;
        protected static void Configure(object obj)
        {
            mapper = null;
        }

        protected static void InitMapper()
        {
            ConfigureHandler handler = new ConfigureHandler(Configure);
            DomSqlMapBuilder builder = new DomSqlMapBuilder();
            
            mapper = builder.ConfigureAndWatch(handler);
        }

        public static ISqlMapper Instance()
        {
            if (mapper == null)
            {
                lock (typeof(SqlMapper))
                {
                    if (mapper == null)
                    {
                        InitMapper();
                    }
                }
            }
            return mapper;
        }

        public static ISqlMapper Get()
        {
            return Instance();
        }
    }
}
