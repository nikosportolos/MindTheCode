using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Timesheet.Models
{
    public class TimesheetEntry
    {
        public int ID { get; set; }
        public DateTime EntryDate { get; set; }
        public User User { get; set; }
        public Project Project { get; set; }
        public int HoursWorked { get; set; }
    }
}
